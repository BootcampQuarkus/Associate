package com.quarkus.bootcamp.nttdata.infraestruture.resources;

import com.quarkus.bootcamp.nttdata.infraestruture.entity.customerWallet.CustomerD;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/customerwallet")
public interface ICustomerWalletApi {
  @GET
  @Path("/")
  Uni<List<CustomerD>> getAll();

  @GET
  @Path("/{id}")
    //@Fallback(fallbackMethod = "fallbackGetById")
  Uni<CustomerD> getById(@PathParam("id") String id);

  default Uni<CustomerD> fallbackGetById(String id) {
    return Uni.createFrom().item(new CustomerD());
  }
}
