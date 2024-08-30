package br.giraffus.service.impl;

import br.giraffus.dto.CategoriaDTO;
import br.giraffus.dto.CategoriaUpdateDTO;
import br.giraffus.dto.responseDTO.CategoriaResponseDTO;
import br.giraffus.dto.responseDTO.UsuarioResponseDTO;
import br.giraffus.model.Categoria;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Usuario;
import br.giraffus.repository.CategoriaRepository;
import br.giraffus.repository.NotificacaoRepository;
import br.giraffus.repository.UsuarioRepository;
import br.giraffus.service.CategoriaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class CategoriaServiceImpl implements CategoriaService {

    public static final Logger LOG = Logger.getLogger(CategoriaServiceImpl.class);

    @Inject
    CategoriaRepository repository;

    @Inject
    NotificacaoRepository notificacaoRepository;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO getPerfilUsuarioLogado() {
        try {
            LOG.info("Requisição UsuarioLogado.getPerfilUsuarioLogado()");
            String login = jsonWebToken.getSubject();

            Usuario usuario = usuarioRepository.findByLogin(login);
            if (usuario == null) {
                LOG.error("Usuário não encontrado para o login: " + login);
                throw new IllegalArgumentException("Usuário não encontrado");
            }

            // Verifique se o objeto 'usuario' é nulo antes de acessar seus métodos
            if (usuario.getEmpresa() == null) {
                LOG.warn("Empresa não encontrada para o usuário: " + login);
            }

            return new UsuarioResponseDTO(usuario);
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição UsuarioLogado.getPerfilUsuarioLogado()", e);
            return null;
        }
    }


    @Override
    public Response getAll(int page, int pageSize) {
        try {
            Usuario u = usuarioRepository.findById(getPerfilUsuarioLogado().id());
            LOG.info("Requisição Categoria.getAll()");
            return Response.ok(repository.findByEmpresa(u.getEmpresa().getId()).stream().filter(EntityClass::getAtivo)
                    .skip((long) (page - 1) * pageSize).limit(pageSize)
                    .map(CategoriaResponseDTO::new)
                    .collect(Collectors.toList())).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.getAll()");
            return Response
                    .status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response getAllSize(){

        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Categoria.getAllSize()");
            return Response.ok(repository.findByEmpresa(u.getEmpresa().getId()).stream().filter(EntityClass::getAtivo)
                    .toList().size()).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.getAllSize()", e);
            return Response.status(400).entity(e.getMessage()).build();

        }
    }

    @Override
    public Response getId(Long id) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Categoria.getId()");
            Categoria categoria = repository.findById(id);
            if(categoria.getAtivo() && categoria.getEmpresa() == u.getEmpresa()) {
                return Response.ok(new CategoriaResponseDTO(categoria)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.getId()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response insert(CategoriaDTO categoriaDTO) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Categoria.insert()");
            Categoria categoria = CategoriaDTO.criaCategoria(categoriaDTO);
            categoria.setEmpresa(u.getEmpresa());
            repository.persist(categoria);
            return Response.ok(new CategoriaResponseDTO(categoria)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.insert()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response update(CategoriaUpdateDTO categoriaUpdateDTO) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Categoria.update()");
            Categoria categoria = repository.findById(categoriaUpdateDTO.id());
            if(!Objects.equals(categoria.getEmpresa(), u.getEmpresa())){
                throw new Exception();
            }
            categoria.setNome(categoriaUpdateDTO.nome());
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.update()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Categoria.delete()");
            Categoria c = new Categoria();
            c = repository.findById(id);
            if(c.getEmpresa() != u.getEmpresa()){
                throw new Exception();
            }
            c.setAtivo(false);

            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Categoria.delete()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}
