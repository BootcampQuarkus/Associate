package com.quarkus.bootcamp.nttdata.infraestruture.resources;

import com.quarkus.bootcamp.nttdata.infraestruture.entity.cardMultiChanel.CardMultiChannelD;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/cards")
public interface ICardMultiChannelApi {
  @GET
  @Path("/")
  Uni<List<CardMultiChannelD>> getAll();

  @GET
  @Path("/{id}")
    //@Fallback(fallbackMethod = "fallbackGetById")
  Uni<CardMultiChannelD> getById(@PathParam("id") String id);

  @GET
  @Path("/serial/{serial}")
    //@Fallback(fallbackMethod = "fallbackGetById")
  Uni<CardMultiChannelD> getBySerial(@PathParam("serial") String serial);

  default Uni<CardMultiChannelD> fallbackGetById(String id) {
    return Uni.createFrom().item(new CardMultiChannelD());
  }
}
