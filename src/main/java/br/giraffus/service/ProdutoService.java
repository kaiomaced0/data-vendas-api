package br.giraffus.service;

import java.util.List;

import br.giraffus.dto.ProdutoDTO;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface ProdutoService {

    public Response getAll(int page, int pageSize);

    public Response getAllSize();

    public Response getId(Long id);
    public Response getIdAdmin(Long id);

    public Response insert(ProdutoDTO produto);

    public Response delete(@PathParam("id") Long id);

    public Response update(Long id, ProdutoDTO produtoDTO);

    public Response addCategoria(Long id, List<Long> categorias);

    public Response estoque();

    public Response addEstoque(Long id, Integer quantidae);
    public Response removeEstoque(Long id, Integer quantidae);
}

