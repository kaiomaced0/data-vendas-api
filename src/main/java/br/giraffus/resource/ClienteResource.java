package br.giraffus.resource;

import br.giraffus.dto.*;
import br.giraffus.service.ClienteService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteService service;

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
    public Response insert(ClienteDTO dto) {
        return service.insert(dto);
    }

    @PUT
    @PermitAll
    public Response update(ClienteUpdateDTO updateDTO) {
        return service.update(updateDTO);
    }

    @PUT
    @PermitAll
    public Response updateDadosEmpresa(ClienteUpdateDadosEmpresaDTO updateDTO) {
        return service.updateDadosEmpresa(updateDTO);
    }

    @PUT
    @PermitAll
    public Response updateDadosCliente(ClienteUpdateDadosClienteDTO updateDTO) {
        return service.updateDadosCliente(updateDTO);
    }

    @PUT
    @PermitAll
    public Response updateEndereco(ClienteUpdateEnderecoDTO updateDTO) {
        return service.updateEndereco(updateDTO);
    }

    @PATCH
    @Path("/delete/{id}")
    @RolesAllowed({"Admin"})
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
