package br.giraffus.service.impl;


import br.giraffus.model.Perfil;
import br.giraffus.model.Usuario;
import io.quarkus.logging.Log;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class TokenJwtService{

    private static final Duration EXPIRATION_TIME = Duration.ofDays(730);


    @Transactional
    public String generateJwt(Usuario usuario) {

        try {
            Instant now = Instant.now();

            Instant expiryDate = now.plus(EXPIRATION_TIME);

            Set<String> roles = new HashSet<>();
            roles = usuario.getPerfis()
                    .stream().map(Perfil::getLabel)
                    .collect(Collectors.toSet());

            Log.info("Requisição TokenJwt.generateJwt()");

            return Jwt.issuer("giraffus-jwt")
                    .subject(usuario.getLogin())
                    .groups(roles)
                    .expiresAt(expiryDate)
                    .sign();

        } catch (Exception e) {
            Log.error("Erro ao rodar Requisição TokenJwt.generateJwt()");
            return null;
        }

    }

}
