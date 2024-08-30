package br.giraffus.service;

import br.giraffus.dto.*;
import jakarta.ws.rs.core.Response;

public interface ClienteService {

    Response getAll(int page, int pageSize);

    public Response getAllSize();

    Response getId(Long id);

    Response insert(ClienteDTO clienteDTO);

    Response update(ClienteUpdateDTO clienteUpdateDTO);

    Response updateDadosEmpresa(ClienteUpdateDadosEmpresaDTO clienteUpdateDadosEmpresaDTO);

    Response updateDadosCliente(ClienteUpdateDadosClienteDTO clienteUpdateDadosClienteDTO);

    Response updateEndereco(ClienteUpdateEnderecoDTO clienteUpdateEnderecoDTO);

    Response delete(Long id);
}
