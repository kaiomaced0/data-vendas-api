package br.giraffus.resource;

import br.giraffus.dto.UsuarioDTO;
import br.giraffus.service.impl.EmpresaService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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
}
