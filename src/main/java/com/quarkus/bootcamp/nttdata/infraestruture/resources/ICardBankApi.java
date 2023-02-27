package com.quarkus.bootcamp.nttdata.infraestruture.resources;

import com.quarkus.bootcamp.nttdata.infraestruture.entity.cardBank.CardD;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/cards")
public interface ICardBankApi {
  @GET
  @Path("/")
  Uni<List<CardD>> getAll(@QueryParam("cardTypeId") Long cardTypeId);

  @GET
  @Path("/{id}")
    //@Fallback(fallbackMethod = "fallbackGetById")
  Uni<CardD> getById(@PathParam("id") Long id);

  default Uni<CardD> fallbackGetById(Long id) {
    return Uni.createFrom().item(new CardD());
  }
}
