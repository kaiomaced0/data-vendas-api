package br.giraffus.resource;

import br.giraffus.dto.NotificacaoDTO;
import br.giraffus.dto.NotificacaoUpdateDTO;
import br.giraffus.dto.responseDTO.NotificacaoResponseDTO;
import br.giraffus.service.NotificacaoService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/notificacaos")
@Produces("application/json")
@Consumes("application/json")
public class NotificacaoResource {

    @Inject
    NotificacaoService service;

    @GET
    @PermitAll
    public List<NotificacaoResponseDTO> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"Sistema"})
    public Response getId(@PathParam("id") Long id) {
        return service.getId(id);
    }

    @POST
    @RolesAllowed({"Sistema"})
    public Response insert(NotificacaoDTO notificacaoDTO) {
        return service.insert(notificacaoDTO);
    }

    @PUT
    @RolesAllowed({"Sistema"})
    public Response update(NotificacaoUpdateDTO notificacaoUpdateDTO) {
        return service.update(notificacaoUpdateDTO);
    }

    @PATCH
    @Path("/delete/{id}")
    @RolesAllowed({"Sistema"})
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
