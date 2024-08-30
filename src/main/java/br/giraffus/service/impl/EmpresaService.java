package br.giraffus.service.impl;

import br.giraffus.dto.EmpresaDTO;
import br.giraffus.dto.UsuarioDTO;
import br.giraffus.dto.responseDTO.EmpresaResponseDTO;
import br.giraffus.model.*;
import br.giraffus.repository.EmpresaRepository;
import br.giraffus.repository.NotificacaoRepository;
import br.giraffus.repository.UsuarioRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmpresaService {

    @Inject
    EmpresaRepository repository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    NotificacaoRepository notificacaoRepository;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    HashService hash;

    @Transactional
    public Response insert(EmpresaDTO dto) {
        try {
            Log.info("Requisição Empresa.insert()");
            Empresa empresa = new Empresa();
            empresa.setNome(dto.nomeEmpresa());

            Usuario usuario = new Usuario();
            usuario.setEmpresa(empresa);
            usuario.setCpf(dto.cpf());
            usuario.setNome(dto.nome());
            usuario.setEmail(dto.email());
            usuario.setSenha(hash.getHashSenha(dto.senha()));
            Set<Perfil> a = new HashSet<Perfil>();
            a.add(Perfil.ADMIN);
            usuario.setPerfis(a);

            usuarioRepository.persist(usuario);

            Notificacao notificacao = new Notificacao();
            notificacao.setTitulo("Empresa criada");
            notificacao.setTipoNotificacao(TipoNotificacao.SUCESSO);
            notificacao.setDescricao("Empresa " + empresa.getNome()+ " criada com sucesso! \n Usuario "+usuario.getNome()+ " de login: "+ usuario.getLogin() + " criado com sucesso!");
            notificacao.setEmpresa(empresa);
            notificacao.setLida(false);
            notificacaoRepository.persist(notificacao);

            repository.persist(empresa);

            return Response.ok(new EmpresaResponseDTO(empresa.getId(), empresa.getNome()) + "\n Usuario login: " + usuario.getLogin()).build();

        } catch (Exception e) {
            Log.error("Erro ao rodar Requisição Empresa.insert()" + e.getMessage());
            return Response.notModified().build();
        }
    }

    @Transactional
    public Response insertFuncionario(UsuarioDTO usuarioDTO){
            try {
                Usuario logado = new Usuario();
                logado = usuarioRepository.findByLogin(jsonWebToken.getSubject());
                if(logado == null){
                    throw new Exception();
                }
                Log.info("Requisição Empresa.insertFuncionario()");
                Usuario usuario = new Usuario();
                usuario.setEmpresa(logado.getEmpresa());
                usuario.setCpf(usuarioDTO.cpf());
                usuario.setNome(usuarioDTO.nome());
                usuario.setEmail(usuarioDTO.email());
                usuario.setSenha(hash.getHashSenha(usuarioDTO.senha()));
                Set<Perfil> a = new HashSet<Perfil>();
                a.add(Perfil.ADMIN);
                usuario.setPerfis(a);
                usuarioRepository.persist(usuario);
                Notificacao notificacao = new Notificacao();
                notificacao.setLida(false);
                notificacao.setTitulo("Funcionario adicionado");
                notificacao.setTipoNotificacao(TipoNotificacao.SUCESSO);
                notificacao.setDescricao("Usuario "+usuario.getNome()+ " de login: "+ usuario.getLogin() + " criado com sucesso!");
                notificacao.setEmpresa(logado.getEmpresa());
                notificacaoRepository.persist(notificacao);

                return Response.ok().build();
            } catch (Exception e) {
                Log.error("Erro ao rodar Requisição Empresa.insertFuncionario()" + e.getMessage());
                return Response.notModified().build();
            }

    }

    @Transactional
    public Response insertMetaMes(Double meta){
        try{
            Log.info("Requisição Empresa.insertMetaMes()");
            Usuario logado = new Usuario();
            logado = usuarioRepository.findByLogin(jsonWebToken.getSubject());
            logado.getEmpresa().setMetaMes(meta);
            return Response.ok().build();
        }catch (Exception e){
            Log.error("Requisição Empresa.insertMetaMes()");
            return Response.noContent().entity(e.getMessage()).build();
        }
    }


    public List<EmpresaResponseDTO> findAll() {
        return repository.listAll().stream().map(e -> new EmpresaResponseDTO(e.getId(), e.getNome())).collect(Collectors.toList());
    }

    public EmpresaResponseDTO findById(Long id) {
        Empresa e = new Empresa();
        e = repository
                .findById(id);
        return new EmpresaResponseDTO(e.getId(), e.getNome());
    }

    @Transactional
    public void deleteById(Long id) {
        repository.findById(id).setAtivo(false);
    }
}
