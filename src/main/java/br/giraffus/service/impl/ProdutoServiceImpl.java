package br.giraffus.service.impl;

import br.giraffus.dto.ProdutoDTO;
import br.giraffus.dto.ProdutoUpdateDTO;
import br.giraffus.dto.responseDTO.EstoqueResponseDTO;
import br.giraffus.dto.responseDTO.ProdutoResponseDTO;
import br.giraffus.model.*;
import br.giraffus.repository.*;
import br.giraffus.service.ProdutoService;
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
public class ProdutoServiceImpl implements ProdutoService {

    public static final Logger LOG = Logger.getLogger(ProdutoServiceImpl.class);

    @Inject
    ProdutoRepository repository;
    @Inject
    FornecedorRepository fornecedorRepository;
    @Inject
    MarcaRepository marcaRepository;
    @Inject
    CategoriaRepository categoriaRepository;

    @Inject
    NotificacaoRepository notificacaoRepository;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    UsuarioRepository usuarioRepository;
    @Override
    public Response getAll(int page, int pageSize) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Produto.getAll()");
            return Response.ok(repository.listAll().stream().filter(p -> p.getEmpresa() == u.getEmpresa()).filter(EntityClass::getAtivo)
                    .skip((long) (page - 1) * pageSize).limit(pageSize)
                    .map(ProdutoResponseDTO::new)
                    .collect(Collectors.toList())).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.getAll()", e);
            return Response.status(400).entity(e.getMessage()).build();

        }
    }

    @Override
    public Response getAllSize(){

        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Produto.getAllSize()");
            return Response.ok(repository.findByEmpresa(u.getEmpresa().getId()).stream().filter(EntityClass::getAtivo)
                    .toList().size()).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.getAllSize()", e);
            return Response.status(400).entity(e.getMessage()).build();

        }
    }


    @Override
    public Response getId(Long id) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Produto.getId()");
            Produto produto = repository.findById(id);
            if (produto.getAtivo() && produto.getEmpresa() == u.getEmpresa()) {
                return Response.ok(new ProdutoResponseDTO(produto)).build();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.getId()", e);
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response insert(ProdutoDTO produtoDTO) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Produto.insert()");
            Produto produto = ProdutoDTO.criaProduto(produtoDTO);
            produto.setFornecedor(fornecedorRepository.findById(produtoDTO.idFornecedor()));
            produto.setMarca(marcaRepository.findById(produtoDTO.idMarca()));
            produto.setEmpresa(u.getEmpresa());
            produtoDTO.idCategoria().forEach(categoria -> produto.getCategorias().add(categoriaRepository.findById(categoria)));
                
            repository.persist(produto);
            Notificacao notificacao = new Notificacao();
            notificacao.setTitulo("Produto adicionado");
            notificacao.setTipoNotificacao(TipoNotificacao.SUCESSO);
            notificacao.setDescricao("Produto " + produto.getNome()+" criado com sucesso!");
            notificacao.setEmpresa(u.getEmpresa());
            notificacaoRepository.persist(notificacao);
            return Response.ok(new ProdutoResponseDTO(produto)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.insert()", e);
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Produto.delete()");
            Produto p =  new Produto();
            p = repository.findById(id);
            if(p.getEmpresa() != u.getEmpresa()){
                throw new Exception();
            }
            p.setAtivo(false);
            return Response.ok().build();
            }
         catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.delete()", e);
             return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response update(Long id, ProdutoUpdateDTO produtoDTO) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Produto.update()");
            Produto produto = repository.findById(id);

            if (produto.getAtivo() && produto.getEmpresa() == u.getEmpresa()) {
                produto.setNome(produtoDTO.nome());
                produto.setDescricao(produtoDTO.descricao());
                produto.setEstoque(produtoDTO.estoque());
                produto.setEstoqueMinimo(produtoDTO.estoqueMinimo());
                produto.setFornecedor(fornecedorRepository.findById(produtoDTO.idFornecedor()));
                produto.setMarca(marcaRepository.findById(produtoDTO.idMarca()));
                produto.setCusto(produtoDTO.custo());
                produto.setValor(produtoDTO.valor());
                produtoDTO.idCategoria().forEach(categoria -> produto.getCategorias().add(categoriaRepository.findById(categoria)));
                return Response.ok().build();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Produto.update()", e);
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response addCategoria(Long id, List<Long> categorias) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Produto.addCategoria()");
            Produto produto = repository.findById(id);

            if (!produto.getAtivo() || produto.getEmpresa() != u.getEmpresa()) {
                throw new Exception();
            }
            if(produto.getCategorias() == null)
                produto.setCategorias(new HashSet<>());

            categorias.forEach(c -> produto.getCategorias().add(categoriaRepository.findById(c)));
            return Response.ok().build();

        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição Produto.addCategoria()", e);
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response estoque() {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try{
            LOG.info("Requisição Produto.estoque()");
            return Response.ok(repository.listAll().stream().filter(p -> p.getEmpresa() == u.getEmpresa()).filter(EntityClass::getAtivo)
                    .map(EstoqueResponseDTO::new)
                    .collect(Collectors.toList())).build();
        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição Produto.estoque()", e);
            return Response.status(400).entity(e.getMessage()).build();

        }
    }

    @Override
    public Response addEstoque(Long id, Integer quantidade) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try{
            LOG.info("Requisição Produto.addEstoque()");
            Produto p = repository.findById(id);

            if(!u.getPerfis().contains(Perfil.SISTEMA)){
                if(u.getEmpresa() != p.getEmpresa()){
                    throw new Exception("Produto não é da mesma empresa!");
                }
            }
            p.setEstoque(p.getEstoque() + quantidade);
            return Response.ok(new EstoqueResponseDTO(p)).build();
        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição Produto.addEstoque()", e);
            return Response.status(400).entity(e.getMessage()).build();

        }
    }

    @Override
    public Response removeEstoque(Long id, Integer quantidade) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try{
            LOG.info("Requisição Produto.removeEstoque()");
            Produto p = repository.findById(id);

            if(!u.getPerfis().contains(Perfil.SISTEMA)){
                if(u.getEmpresa() != p.getEmpresa()){
                    throw new Exception("Produto não é da mesma empresa!");
                }
            }
            p.setEstoque(p.getEstoque() - quantidade);
            return Response.ok(new EstoqueResponseDTO(p)).build();
        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição Produto.removeEstoque()", e);
            return Response.status(400).entity(e.getMessage()).build();

        }
    }
}
