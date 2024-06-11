package br.giraffus.service.impl;

import br.giraffus.dto.MarcaDTO;
import br.giraffus.dto.MarcaUpdateDTO;
import br.giraffus.dto.responseDTO.MarcaResponseDTO;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Marca;
import br.giraffus.model.Usuario;
import br.giraffus.repository.MarcaRepository;
import br.giraffus.repository.NotificacaoRepository;
import br.giraffus.repository.UsuarioRepository;
import br.giraffus.service.MarcaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MarcaServiceImpl implements MarcaService {

    public static final Logger LOG = Logger.getLogger(MarcaServiceImpl.class);

    @Inject
    MarcaRepository repository;
    @Inject
    NotificacaoRepository notificacaoRepository;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public List<MarcaResponseDTO> getAll() {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Marca.getAll()");
            return repository.findAll().stream().filter(m -> m.getEmpresa() == u.getEmpresa()).filter(EntityClass::getAtivo)
                    .map(MarcaResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Marca.getAll()");
            return null;
        }
    }

    @Override
    public Response getId(Long id) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Marca.getId()");
            Marca marca = repository.findById(id);
            if(marca.getAtivo() && u.getEmpresa() == marca.getEmpresa()){
                return Response.ok(new MarcaResponseDTO(marca)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Marca.getId()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response insert(MarcaDTO marcaDTO) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Marca.insert()");
            Marca marca = MarcaDTO.criaMarca(marcaDTO);
            marca.setEmpresa(u.getEmpresa());
            repository.persist(marca);
            return Response.ok(new MarcaResponseDTO(marca)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Marca.insert()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Marca.delete()");
            Marca marca = new Marca();
            marca = repository.findById(id);
            if(marca.getEmpresa() != u.getEmpresa()){
                throw new Exception();
            }
            marca.setAtivo(false);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Marca.delete()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
    @Override
    public Response update(MarcaUpdateDTO marcaUpdateDTO) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Marca.update()");
            Marca marca = repository.findById(marcaUpdateDTO.id());
            if(marca.getAtivo()&& marca.getEmpresa() == u.getEmpresa()){
                marca.setNome(marcaUpdateDTO.nome());
                return Response.ok().build();
            }else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Marca.update()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}
