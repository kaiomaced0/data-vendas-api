package br.giraffus.resource;

import br.giraffus.dto.CategoriaDTO;
import br.giraffus.dto.CategoriaUpdateDTO;
import br.giraffus.service.CategoriaService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/categorias")
@Produces("application/json")
@Consumes("application/json")
public class CategoriaResource {

    @Inject
    CategoriaService service;

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

    @GET
    @Path("/{id}")
    @PermitAll
    public Response getId(@PathParam("id") Long id) {
        return service.getId(id);
    }

    @POST
    @RolesAllowed({"Admin"})
    public Response insert(CategoriaDTO categoriaDTO) {
        return service.insert(categoriaDTO);
    }

    @PUT
    @RolesAllowed({"Admin"})
    public Response update(CategoriaUpdateDTO categoriaUpdateDTO) {
        return service.update(categoriaUpdateDTO);
    }

    @PATCH
    @Path("/delete/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
