package br.giraffus.resource;

import br.giraffus.dto.VendaDTO;
import br.giraffus.dto.responseDTO.VendaResponseDTO;
import br.giraffus.service.VendaService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/vendas")
@Produces("application/json")
@Consumes("application/json")
public class VendaResource {

    @Inject
    VendaService service;

    @GET
    @PermitAll
    public List<VendaResponseDTO> getAll() {
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
    public Response insert(VendaDTO VendaDTO) {
        return service.insert(VendaDTO);
    }

//    @PUT
//    @PermitAll
//    public Response update(VendaUpdateDTO VendaUpdateDTO) {
//        return service.update(VendaUpdateDTO);
//    }

    @PATCH
    @Path("/delete/{id}")
    @PermitAll
    public Response delete(@PathParam("id") Long id) {
        return service.delete(id);
    }
}
