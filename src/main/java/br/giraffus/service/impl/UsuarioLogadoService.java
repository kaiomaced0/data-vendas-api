package br.giraffus.service.impl;

import br.giraffus.dto.responseDTO.UsuarioResponseDTO;
import br.giraffus.model.Usuario;
import br.giraffus.repository.UsuarioRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class UsuarioLogadoService {

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    UsuarioRepository usuarioRepository;

    public Response getPerfilUsuarioLogado() {

        String login = jsonWebToken.getSubject();
        try {
            Usuario u = usuarioRepository.findByLogin(login);
            Log.info("Requisição UsuarioLogado.getPerfilUsuarioLogado()");
            return Response.ok(new UsuarioResponseDTO(u)).build();
        } catch (Exception e) {
            Log.error("Erro ao rodar Requisição UsuarioLogado.getPerfilUsuarioLogado()");
            return Response.status(400).entity(e.getMessage()).build();
        }

    }

}



