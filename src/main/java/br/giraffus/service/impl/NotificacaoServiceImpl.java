package br.giraffus.service.impl;

import br.giraffus.dto.NotificacaoDTO;
import br.giraffus.dto.NotificacaoUpdateDTO;
import br.giraffus.dto.responseDTO.NotificacaoResponseDTO;
import br.giraffus.model.Empresa;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Notificacao;
import br.giraffus.model.Usuario;
import br.giraffus.repository.EmpresaRepository;
import br.giraffus.repository.NotificacaoRepository;
import br.giraffus.repository.UsuarioRepository;
import br.giraffus.service.NotificacaoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class NotificacaoServiceImpl implements NotificacaoService {

    public static final Logger LOG = Logger.getLogger(NotificacaoServiceImpl.class);

    @Inject
    NotificacaoRepository repository;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    EmpresaRepository empresaRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public List<NotificacaoResponseDTO> getAll() {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Notificacao.getAll()");
            return repository.listAll().stream().filter(n -> n.getEmpresa() == u.getEmpresa()).filter(EntityClass::getAtivo).filter(Notificacao::getLida)
                    .map(NotificacaoResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Notificacao.getAll()");
            return null;
        }
    }

    @Override
    public Response getId(Long id) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Notificacao.getId()");
            Notificacao notificacao = repository.findById(id);
            if(notificacao.getAtivo() && u.getEmpresa() == notificacao.getEmpresa()) {
                return Response.ok(new NotificacaoResponseDTO(notificacao)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Notificacao.getId()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response lida(Long id) {
        try {
            LOG.info("Requisição Notificacao.lida()");
            repository.findById(id).setLida(true);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Notificacao.lida()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response insert(NotificacaoDTO notificacaoDTO) {
        try {
            LOG.info("Requisição Notificacao.insert()");
            Notificacao notificacao = NotificacaoDTO.criaNotificacao(notificacaoDTO);
            Empresa e = empresaRepository.findById(notificacaoDTO.idEmpresa());
            notificacao.setEmpresa(e);
            repository.persist(notificacao);
            return Response.ok(new NotificacaoResponseDTO(notificacao)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Notificacao.insert()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response update(NotificacaoUpdateDTO notificacaoUpdateDTO) {
        try {
            LOG.info("Requisição Notificacao.update()");
            Notificacao notificacao = repository.findById(notificacaoUpdateDTO.id());
            notificacao.setTitulo(notificacaoUpdateDTO.titulo());
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Notificacao.update()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        try {
            LOG.info("Requisição Notificacao.delete()");
            repository.findById(id).setAtivo(false);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Notificacao.delete()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}
