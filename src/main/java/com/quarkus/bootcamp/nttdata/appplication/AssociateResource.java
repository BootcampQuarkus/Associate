package com.quarkus.bootcamp.nttdata.appplication;

import com.quarkus.bootcamp.nttdata.domain.enitty.Associate;
import com.quarkus.bootcamp.nttdata.domain.services.AssociateService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/associate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AssociateResource {
  @Inject
  AssociateService service;

  @POST
  public Uni<String> add(Associate associate) {
    return service.add(associate);
  }
}
