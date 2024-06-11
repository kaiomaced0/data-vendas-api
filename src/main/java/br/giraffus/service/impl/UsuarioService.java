package br.giraffus.service.impl;

import br.giraffus.dto.AuthUsuarioDTO;
import br.giraffus.dto.UsuarioDTO;
import br.giraffus.dto.UsuarioUpdateSenhaDTO;
import br.giraffus.dto.responseDTO.UsuarioResponseDTO;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Perfil;
import br.giraffus.model.Usuario;
import br.giraffus.repository.EmpresaRepository;
import br.giraffus.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioService{

    public static final Logger LOG = Logger.getLogger(String.valueOf(UsuarioService.class));

    @Inject
    UsuarioRepository repository;

    @Inject
    UsuarioLogadoService usuarioLogado;

    @Inject
    HashService hash;

    @Inject
    EmpresaRepository empresaRepository;

    @Inject
    JsonWebToken jsonWebToken;


    public List<UsuarioResponseDTO> getAll() {

        try {
            LOG.info("Requisição Usuario.getAll()");

            return repository.findAll().stream().filter(EntityClass::getAtivo)
                    .sorted(Comparator.comparing(EntityClass::getId))
                    .map(UsuarioResponseDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Usuario.getAll()");
            return null;
        }

    }

    public Response getId(long id) {
        try {
            LOG.info("Requisição Usuario.getId()");
            Usuario u = repository.findById(id);
            return Response.ok(new UsuarioResponseDTO(u)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Usuario.getId()");
            return Response.status(400).entity(e.getMessage()).build();
        }

    }

    @Transactional
    public Response update(Long id, UsuarioDTO u) {
        try {
            LOG.info("Requisição Usuario.update()");
            Usuario user = new Usuario();
            user = repository.findById(id);
            if(!u.nome().isEmpty()){
                user.setNome(u.nome());
            }
            if(!u.email().isEmpty()){
                user.setEmail(u.email());
            }
            if(!u.login().isEmpty()){
                user.setLogin(u.login());
            }
            if(!u.senha().isEmpty()){
                user.setSenha(hash.getHashSenha(u.senha()));
            }
            if(!u.cpf().isEmpty())
                user.setCpf(u.cpf());

            return Response.ok(new UsuarioResponseDTO(user)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Usuario.update()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    public Response getNome(String nome) {
        try {
            LOG.info("Requisição Usuario.getNome()");

            return Response.ok(repository.findByNome(nome)
                    .stream()
                    .map(UsuarioResponseDTO::new)
                    .collect(Collectors.toList())).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Usuario.getNome()");
            return Response.status(400).entity(e.getMessage()).build();
        }

    }

    @Transactional
    public Response updateSenha(UsuarioUpdateSenhaDTO senha) {
        try {
            LOG.info(" - Requisição Usuario.updateSenha() ");

            Usuario entity = repository.findByLogin(senha.login());

            if(!hash.getHashSenha(senha.senhaAnterior()).equals(entity.getSenha()))
                throw new Exception("Senha anterior Incorreta");

            entity.setSenha(hash.getHashSenha(senha.novaSenha()));
            return Response.ok(new UsuarioResponseDTO(entity)).build();
        } catch (Exception e) {
            LOG.error(" - Erro ao rodar Requisição Usuario.updateSenha()");
            return Response.status(400).entity(e.getMessage()).build();
        }

    }

//    @Transactional
//    public UsuarioResponseDTO updateLogin(Long id, UsuarioUpdateLoginDTO login) {
//        try {
//            LOG.info(usuarioLogado.getPerfilUsuarioLogado().id().toString() + " - Requisição Usuario.updateLogin()");
//
//            Usuario entity = repository.findById(id);
//            if(!hash.getHashSenha(login.senha()).equals(entity.getSenha()))
//                throw new NotFoundException("Senha Incorreta");
//
//            entity.setLogin(login.login());
//            return new UsuarioResponseDTO(entity);
//        } catch (Exception e) {
//            LOG.error(usuarioLogado.getPerfilUsuarioLogado().id().toString() + " - Erro ao rodar Requisição Usuario.updateLogin()");
//            return null;
//        }
//
//    }

//    @Transactional
//    public UsuarioResponseDTO updateNome(Long id, UsuarioUpdateNomeDTO nome) {
//        try {
//            LOG.info(usuarioLogado.getPerfilUsuarioLogado().id().toString() + " - Requisição Usuario.updateNome()");
//
//            Usuario entity = repository.findById(id);
//            if(entity instanceof PessoaFisica){
//                if(!hash.getHashSenha(nome.senha()).equals(entity.getSenha()))
//                    throw new NotFoundException("Senha Incorreta");
//                PessoaFisica p = pessoaFisicaRepository.findById(entity.getId());
//                p.setNome(nome.nome());
//            }
//
//            return new UsuarioResponseDTO(entity);
//        } catch (Exception e) {
//            LOG.error(usuarioLogado.getPerfilUsuarioLogado().id().toString() + " - Erro ao rodar Requisição Usuario.updateNome()");
//            return null;
//        }
//
//    }

//    @Transactional
//    public UsuarioResponseDTO updateEmail(Long id, UsuarioUpdateEmailDTO email) {
//        try {
//            LOG.info(usuarioLogado.getPerfilUsuarioLogado().id().toString() + " - Requisição Usuario.updateEmail()");
//
//            Usuario entity = repository.findById(id);
//            if(!hash.getHashSenha(email.senha()).equals(entity.getSenha()))
//                throw new NotFoundException("Senha Incorreta");
//
//            entity.setEmail(email.email());
//            return new UsuarioResponseDTO(entity);
//        } catch (Exception e) {
//            LOG.error(usuarioLogado.getPerfilUsuarioLogado().id().toString() + " - Erro ao rodar Requisição Usuario.updateEmail()");
//            return null;
//        }
//
//    }

    @Transactional
    public Response insert(UsuarioDTO user) {
        Usuario u = repository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Usuario.insert()");
            Usuario teste = new Usuario();
            teste = repository.findByLogin(user.login());
            if(teste != null){
                throw new Exception("Usuario com esse login ja existe!");
            }
            Usuario usuario = new Usuario();
            usuario.setNome(user.nome());
            usuario.setCpf(user.cpf());
            usuario.setEmail(user.email());
            usuario.setLogin(user.login());
            usuario.setSenha(hash.getHashSenha(user.senha()));
            usuario.setEmpresa(u.getEmpresa());
            usuario.setPerfis(new HashSet<>());
            usuario.getPerfis().add(Perfil.valueOf(2));
            if(user.admin())
                usuario.getPerfis().add(Perfil.valueOf(1));

            repository.persist(usuario);
            return Response.ok(new UsuarioResponseDTO(usuario)).build();

        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Usuario.insert()" + e.getMessage());
            return Response.status(400).entity(e.getMessage()).build();
        }

    }

//    @Transactional
//    public UsuarioResponseDTO updateImagem(long id, String nomeImagem) {
//        try {
//            LOG.info("Requisição Usuario.updateImagem()");
//
//            Usuario entity = repository.findById(id);
//            entity.setImage(nomeImagem);
//
//            return new UsuarioResponseDTO(entity);
//        } catch (Exception e) {
//            LOG.error("Erro ao rodar Requisição Usuario.updateImagem()");
//            return null;
//        }
//    }


//    public UsuarioResponseDTO findByLogin(String login) {
//        try {
//            LOG.info("Requisição Usuario.findByLogin()");
//
//            Usuario usuario = repository.findByLogin(login);
//            if (usuario == null)
//                throw new NotFoundException("Usuario não encontrado");
//
//            return new UsuarioResponseDTO(usuario);
//        } catch (Exception e) {
//            LOG.error("Erro ao rodar Requisição Usuario.findByLogin()");
//            return null;
//        }
//
//    }


    @Transactional
    public Response delete(Long id) {
        Usuario u = repository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Usuario.delete()");

            Usuario entity = repository.findById(id);
            if(!u.getPerfis().contains(Perfil.SISTEMA)){
                if(u.getEmpresa() != u.getEmpresa())
                    throw new Exception("Usuarios não são da mesma empresa!");
            }

            entity.setAtivo(false);

            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Usuario.delete()" + e.getMessage());
            return Response.status(400).entity(e.getMessage()).build();
        }
    }


    @Transactional
    public Response resetarSenha(Long id) {
        Usuario user = repository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("entrou resetarSenha");
            Usuario u = repository.findById(id);

            if(!user.getPerfis().contains(Perfil.SISTEMA)){
                if(user.getEmpresa() != u.getEmpresa())
                    throw new Exception("Usuarios não são da mesma empresa!");
            }
            u.setSenha(hash.getHashSenha("123"));
            return Response.ok().build();
        }catch (Exception e){
            LOG.error("erro resetarSenha - " + e.getMessage());
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    public Usuario byLoginAndSenha(AuthUsuarioDTO authDTO){
        String senha = hash.getHashSenha(authDTO.senha());
        Usuario usuario = repository.findByLoginAndSenha(authDTO.login(), senha);
        if(usuario == null){
            usuario = repository.findByCpfAndSenha(authDTO.login(), senha);
        }
        return usuario;
    }

}
