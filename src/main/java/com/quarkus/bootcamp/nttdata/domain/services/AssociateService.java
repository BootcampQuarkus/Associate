package com.quarkus.bootcamp.nttdata.domain.services;

import com.quarkus.bootcamp.nttdata.domain.enitty.Associate;
import com.quarkus.bootcamp.nttdata.infraestruture.entity.cardBank.CardD;
import com.quarkus.bootcamp.nttdata.infraestruture.entity.cardMultiChanel.CardMultiChannelD;
import com.quarkus.bootcamp.nttdata.infraestruture.entity.token.AssociationD;
import com.quarkus.bootcamp.nttdata.infraestruture.entity.token.ValueD;
import com.quarkus.bootcamp.nttdata.infraestruture.resources.ICardBankApi;
import com.quarkus.bootcamp.nttdata.infraestruture.resources.ICardMultiChannelApi;
import com.quarkus.bootcamp.nttdata.infraestruture.resources.ICustomerWalletApi;
import com.quarkus.bootcamp.nttdata.infraestruture.resources.ITokenApi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class AssociateService {
  @RestClient
  ICustomerWalletApi customerWalletApi;
  @RestClient
  ICardBankApi cardBankApi;
  @RestClient
  ICardMultiChannelApi cardMultiChannelApi;
  @RestClient
  ITokenApi tokenApi;
  public Uni<String> add(Associate associate) {
    // Busca el usuario
    return customerWalletApi.getById(associate.getId()).flatMap(cu -> {
      if (cu == null) {
        throw new NotFoundException("Customer not found");
      }
      // Buscar tarjeta en el banco
      return this.callCardsCustomer(associate).flatMap(cd -> {
        if (cd == null) {
          throw new NotFoundException("Card not found");
        }
        // Buscar si la tarjeta esta en el multicanal
        return cardMultiChannelApi.getBySerial(associate.getSerial()).flatMap(cmc -> {
          // Si la tarjeta existe en el multicanal, generar código
          return generateKey(cmc, associate).flatMap(gt -> Uni.createFrom().item(gt.getKey()));
        });
      });
    });
    // Guardar codigo en redis
  }

  public Uni<AssociationD> generateKey(CardMultiChannelD cmc, Associate associate) {
    Integer key = ThreadLocalRandom.current().nextInt(10000000, 100000000);
    Long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
    return tokenApi.create(new AssociationD(key.toString(), new ValueD(timestamp.toString(), cmc.getUserId(), associate.getId())));
  }

  public Uni<CardD> callCardsCustomer(Associate associate) {
    Uni<List<CardD>> cards = cardBankApi.getAll(2L);
    return cards.onItem().<CardD>disjoint()
          .filter(uc -> (uc.getSerial().equals(associate.getSerial())
                && uc.getPin().equals(associate.getPin())
                && uc.getCvv().equals(associate.getCvv())))
          .collect().first();
  }
}