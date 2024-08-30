package br.giraffus.service.impl;

import br.giraffus.dto.FornecedorDTO;
import br.giraffus.dto.FornecedorUpdateDTO;
import br.giraffus.dto.responseDTO.FornecedorResponseDTO;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Fornecedor;
import br.giraffus.model.Usuario;
import br.giraffus.repository.CategoriaRepository;
import br.giraffus.repository.FornecedorRepository;
import br.giraffus.repository.NotificacaoRepository;
import br.giraffus.repository.UsuarioRepository;
import br.giraffus.service.FornecedorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    public static final Logger LOG = Logger.getLogger(FornecedorServiceImpl.class);

    @Inject
    FornecedorRepository repository;

    @Inject
    CategoriaRepository categoriaRepository;

    @Inject
    NotificacaoRepository notificacaoRepository;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public List<FornecedorResponseDTO> getAll() {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Fornecedor.getAll()");
            return repository.findByEmpresa(u.getEmpresa().getId()).stream()
                    .filter(EntityClass::getAtivo)
                    .map(FornecedorResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.getAll()");
            return null;
        }
    }

    @Override
    public Response getId(Long id) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Fornecedor.getId()");
            Fornecedor fornecedor = repository.findById(id);
            if(fornecedor.getAtivo() && u.getEmpresa() == fornecedor.getEmpresa()) {
                return Response.ok(new FornecedorResponseDTO(fornecedor)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.getId()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response insert(FornecedorDTO fornecedorDTO) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Fornecedor.insert()");
            Fornecedor fornecedor =  FornecedorDTO.criaFornecedor(fornecedorDTO);
            fornecedor.setEmpresa(u.getEmpresa());
            repository.persist(fornecedor);
            return Response.ok(new FornecedorResponseDTO(fornecedor)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.insert()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response update(Long id, FornecedorUpdateDTO dto) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Fornecedor.update()");
            Fornecedor fornecedor = repository.findById(id);
            if(fornecedor.getAtivo() && u.getEmpresa() == fornecedor.getEmpresa()){

                return Response.ok().build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.update()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Fornecedor.delete()");
            Fornecedor f = repository.findById(id);
            if(!f.getAtivo() && u.getEmpresa() != f.getEmpresa()){
                throw new Exception();
            }
            f.setAtivo(false);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Fornecedor.delete()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}
