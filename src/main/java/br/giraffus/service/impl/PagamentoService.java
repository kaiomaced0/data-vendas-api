package br.giraffus.service.impl;

import br.giraffus.dto.EmpresaDTO;
import br.giraffus.dto.UsuarioDTO;
import br.giraffus.dto.responseDTO.EmpresaResponseDTO;
import br.giraffus.dto.responseDTO.PagamentoResponseDTO;
import br.giraffus.model.*;
import br.giraffus.repository.NotificacaoRepository;
import br.giraffus.repository.PagamentoRepository;
import br.giraffus.repository.UsuarioRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class PagamentoService {

    public static final Logger LOG = Logger.getLogger(ProdutoServiceImpl.class);
    @Inject
    PagamentoRepository repository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    NotificacaoRepository notificacaoRepository;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    HashService hash;

    public Response getAll(int page, int pageSize) {
        LOG.info("Requisição Pagamento.getAll()");
        return Response.ok(repository.listAll().stream().skip((long) (page - 1) * pageSize).limit(pageSize).map(PagamentoResponseDTO::new).collect(Collectors.toList())).build();
    }
    public Response getAllSize() {
        LOG.info("Requisição Pagamento.getAllSize()");
        return Response.ok(repository.listAll().stream().toList().size()).build();
    }

}
