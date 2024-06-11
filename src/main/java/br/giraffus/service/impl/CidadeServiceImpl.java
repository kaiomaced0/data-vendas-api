package br.giraffus.service.impl;

import br.giraffus.dto.CidadeDTO;
import br.giraffus.dto.CidadeUpdateDTO;
import br.giraffus.dto.responseDTO.CidadeResponseDTO;
import br.giraffus.model.Cidade;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Estado;
import br.giraffus.model.Usuario;
import br.giraffus.repository.CidadeRepository;
import br.giraffus.repository.UsuarioRepository;
import br.giraffus.service.CidadeService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.stream.Collectors;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {

    public static final Logger LOG = Logger.getLogger(CidadeServiceImpl.class);

    @Inject
    CidadeRepository repository;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public Response getAll() {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Cidade.getAll()");
            return Response.ok(repository.listAll().stream().filter(EntityClass::getAtivo)
                    .map(CidadeResponseDTO::new)
                    .collect(Collectors.toList())).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cidade.getAll()", e);
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response getId(Long id) {
        try {
            LOG.info("Requisição Cidade.getId()");
            Cidade cidade = repository.findById(id);
            if (cidade.getAtivo()) {
                return Response.ok(new CidadeResponseDTO(cidade)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cidade.getId()", e);
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
    @Override
    public Response getNome(String nome) {
        try {
            LOG.info("Requisição Cidade.getNome()");
            return Response.ok(repository.findByNome(nome).stream().filter(EntityClass::getAtivo).map(CidadeResponseDTO::new).collect(Collectors.toList())).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cidade.getId()", e);
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response insert(CidadeDTO cidadeDTO) {
        try {
            LOG.info("Requisição Cidade.insert()");
            Cidade cidade = CidadeDTO.criaCidade(cidadeDTO);
            cidade.setEstado(Estado.valueOf(cidadeDTO.estado()));
            repository.persist(cidade);
            return Response.ok(new CidadeResponseDTO(cidade)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cidade.insert()", e);
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response update(CidadeUpdateDTO cidadeUpdateDTO) {
        try {
            LOG.info("Requisição Cidade.update()");
            Cidade existingCidade = repository.findById(cidadeUpdateDTO.id());
            if (existingCidade != null && existingCidade.getAtivo()) {
                existingCidade.setNome(cidadeUpdateDTO.nome());
                existingCidade.setEstado(Estado.valueOf(cidadeUpdateDTO.estadoId()));
                return Response.ok().build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cidade.update()", e);
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        try {
            LOG.info("Requisição Cidade.delete()");
            Cidade cidade = repository.findById(id);
            if (cidade != null) {
                cidade.setAtivo(false);
                return Response.ok().build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cidade.delete()", e);
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}
