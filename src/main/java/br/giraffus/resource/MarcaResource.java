package br.giraffus.resource;

import br.giraffus.dto.MarcaDTO;
import br.giraffus.dto.MarcaUpdateDTO;
import br.giraffus.dto.responseDTO.MarcaResponseDTO;
import br.giraffus.service.MarcaService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/marcas")
@Produces("application/json")
@Consumes("application/json")
public class MarcaResource {

    @Inject
    MarcaService service;

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
    @PermitAll
    public Response insert(MarcaDTO marcaDTO) {
        return service.insert(marcaDTO);
    }

    @PATCH
    @PermitAll
    public Response update(MarcaUpdateDTO marcaUpdateDTO) {
        return service.update(marcaUpdateDTO);
    }

    @PATCH
    @Path("/delete/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
