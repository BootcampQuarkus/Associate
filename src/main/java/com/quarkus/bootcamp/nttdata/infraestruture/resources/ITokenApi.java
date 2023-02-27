package com.quarkus.bootcamp.nttdata.infraestruture.resources;

import com.quarkus.bootcamp.nttdata.infraestruture.entity.token.AssociationD;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/token")
public interface ITokenApi {
  @GET
  @Path("/")
  Uni<List<AssociationD>> getAll();

  @GET
  @Path("/{id}")
  @Fallback(fallbackMethod = "fallbackGetById")
  Uni<AssociationD> getById(@PathParam("id") String id);

  @POST
  @Path("/")
  @Fallback(fallbackMethod = "fallbackCreate")
  Uni<AssociationD> create(AssociationD associationD);

  default Uni<AssociationD> fallbackGetById(String id) {
    return Uni.createFrom().item(new AssociationD());
  }

  default Uni<AssociationD> fallbackCreate(AssociationD associationD) {
    return Uni.createFrom().item(new AssociationD());
  }
}
