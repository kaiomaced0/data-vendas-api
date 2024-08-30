package br.giraffus.resource;

import br.giraffus.dto.UsuarioDTO;
import br.giraffus.service.impl.EmpresaService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/empresa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpresaResource {
    @Inject
    EmpresaService empresaService;

    @POST
    @PermitAll
    public Response insertFuncionario(UsuarioDTO usuarioDTO){
        return empresaService.insertFuncionario(usuarioDTO);
    }

    @PATCH
    @PermitAll
    @Path("/metames/{meta}")
    public Response insertMetaMes(@PathParam("meta") Double meta){
        return empresaService.insertMetaMes(meta);
    }
}
