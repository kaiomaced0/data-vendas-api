package br.giraffus.resource;

import br.giraffus.service.impl.PagamentoService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/pagamentos")
@Produces("application/json")
@Consumes("application/json")
public class PagamentoResource {

    @Inject
    PagamentoService service;

    @GET
    @PermitAll
    @Path("/{page}/{pageSize}")
    public Response getAll(@PathParam("page") int page, @PathParam("pageSize") int pageSize) {
        return service.getAll(page, pageSize);
    }
    @GET
    @PermitAll
    @Path("/size")
    public Response getAllSize() {
        return service.getAllSize();
    }

}
