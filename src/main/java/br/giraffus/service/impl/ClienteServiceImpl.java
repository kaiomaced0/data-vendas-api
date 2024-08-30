package br.giraffus.service.impl;

import br.giraffus.dto.*;
import br.giraffus.dto.responseDTO.ClienteResponseDTO;
import br.giraffus.dto.responseDTO.ProdutoResponseDTO;
import br.giraffus.model.Cliente;
import br.giraffus.model.EntityClass;
import br.giraffus.model.Usuario;
import br.giraffus.repository.CidadeRepository;
import br.giraffus.repository.ClienteRepository;
import br.giraffus.repository.NotificacaoRepository;
import br.giraffus.repository.UsuarioRepository;
import br.giraffus.service.ClienteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.stream.Collectors;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteService {

    public static final Logger LOG = Logger.getLogger(ClienteServiceImpl.class);

    @Inject
    ClienteRepository repository;

    @Inject
    CidadeRepository cidadeRepository;
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
            LOG.info("Requisição Cliente.getAll()");
            return Response.ok(repository.listAll().stream().filter(c -> c.getEmpresa() == u.getEmpresa()).filter(EntityClass::getAtivo)
                    .skip((long) (page - 1) * pageSize).limit(pageSize)
                    .map(ClienteResponseDTO::new)
                    .collect(Collectors.toList())).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.getAll()");
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
            LOG.info("Requisição Cliente.getId()");
            Cliente cliente = repository.findById(id);
            if(cliente.getAtivo() && cliente.getEmpresa() == u.getEmpresa()) {
                return Response.ok(new ClienteResponseDTO(cliente)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.getId()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response insert(ClienteDTO clienteDTO) {

        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Cliente.insert()");
            Cliente cliente = ClienteDTO.criaCliente(clienteDTO);
            cliente.setEmpresa(u.getEmpresa());
            cliente.setCidade(cidadeRepository.findById(clienteDTO.idCidade()));
            repository.persist(cliente);
            return Response.ok(new ClienteResponseDTO(cliente)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.insert()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response update(ClienteUpdateDTO clienteUpdateDTO) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Cliente.update()");
            Cliente cliente = repository.findById(clienteUpdateDTO.id());
            if (cliente.getAtivo() && cliente.getEmpresa() == u.getEmpresa()) {
                if (clienteUpdateDTO.nomeCliente() != null)
                    cliente.setNomeCliente(clienteUpdateDTO.nomeCliente());
                if (clienteUpdateDTO.nomeEmpresa() != null)
                    cliente.setNomeEmpresa(clienteUpdateDTO.nomeEmpresa());
                if (clienteUpdateDTO.cnpj() != null)
                    cliente.setCnpj(clienteUpdateDTO.cnpj());
                if (clienteUpdateDTO.cpfCliente() != null)
                    cliente.setCpfCliente(clienteUpdateDTO.cpfCliente());
                if (clienteUpdateDTO.endereco() != null)
                    cliente.setEndereco(clienteUpdateDTO.endereco());

                return Response.ok(new ClienteResponseDTO(cliente)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.update()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Cliente.delete()");
            Cliente cliente = repository.findById(id);
            if (cliente != null && cliente.getEmpresa() == u.getEmpresa()) {
                cliente.setAtivo(false);
                return Response.ok().build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.delete()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response updateDadosEmpresa(ClienteUpdateDadosEmpresaDTO clienteUpdateDadosEmpresaDTO) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Cliente.update()");
            Cliente cliente = repository.findById(clienteUpdateDadosEmpresaDTO.id());
            if (cliente.getAtivo() && cliente.getEmpresa() == u.getEmpresa()) {
                cliente.setNomeEmpresa(clienteUpdateDadosEmpresaDTO.nomeEmpresa());
                cliente.setCnpj(clienteUpdateDadosEmpresaDTO.cnpj());
                return Response.ok(new ClienteResponseDTO(cliente)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.update()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response updateDadosCliente(ClienteUpdateDadosClienteDTO clienteUpdateDadosClienteDTO) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        try {
            LOG.info("Requisição Cliente.update()");
            Cliente cliente = repository.findById(clienteUpdateDadosClienteDTO.id());
            if (cliente.getAtivo() && cliente.getEmpresa() == u.getEmpresa()) {
                cliente.setNomeCliente(clienteUpdateDadosClienteDTO.nomeCliente());
                cliente.setCpfCliente(clienteUpdateDadosClienteDTO.cpfCliente());
                return Response.ok(new ClienteResponseDTO(cliente)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.update()");
            return Response.status(400).entity(e.getMessage()).build();
        }    
    }

    @Override
    public Response updateEndereco(ClienteUpdateEnderecoDTO clienteUpdateEnderecoDTO) {
        Usuario u = usuarioRepository.findByLogin(jsonWebToken.getSubject());
        
        try {
            LOG.info("Requisição Cliente.update()");
            Cliente cliente = repository.findById(clienteUpdateEnderecoDTO.id());
            if (cliente.getAtivo() && u.getEmpresa() == cliente.getEmpresa()) {
                cliente.setCidade(cidadeRepository.findById(clienteUpdateEnderecoDTO.cidade()));
                cliente.setEndereco(clienteUpdateEnderecoDTO.endereco());
                return Response.ok(new ClienteResponseDTO(cliente)).build();
            }
            else{
                throw new Exception();
            }
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição Cliente.update()");
            return Response.status(400).entity(e.getMessage()).build();
        }
    
    }
}
