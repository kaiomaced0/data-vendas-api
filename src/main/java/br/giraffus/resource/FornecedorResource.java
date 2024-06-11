package br.giraffus.resource;

import br.giraffus.dto.FornecedorDTO;
import br.giraffus.dto.FornecedorUpdateDTO;
import br.giraffus.dto.responseDTO.FornecedorResponseDTO;
import br.giraffus.service.FornecedorService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/fornecedores")
@Produces("application/json")
@Consumes("application/json")
public class FornecedorResource {

    @Inject
    FornecedorService service;

    @GET
    @PermitAll
    public List<FornecedorResponseDTO> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response getId(@PathParam("id") Long id) {
        return service.getId(id);
    }

    @POST
    @PermitAll
    public Response insert(FornecedorDTO fornecedorDTO) {
        return service.insert(fornecedorDTO);
    }

    @PUT
    @RolesAllowed({"Admin"})
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, FornecedorUpdateDTO fornecedorUpdateDTO) {
        return service.update(id, fornecedorUpdateDTO);
    }

    @PATCH
    @Path("/delete/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
