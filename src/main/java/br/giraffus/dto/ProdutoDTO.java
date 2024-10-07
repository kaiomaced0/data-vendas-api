package br.giraffus.dto;

import br.giraffus.model.Produto;

import java.util.HashSet;
import java.util.List;

public record ProdutoDTO(
        String nome,
        String nomeLongo,
        String descricao,
        Integer estoque,
        Double valorCompra,
        Double preco,
        Long idFornecedor,
        Integer estoqueMinimo,
        Long idMarca,
        List<Long> idCategoria,
        String codigo,
        String codigoBarras) {

    public static Produto criaProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.nome());
        produto.setNomeLongo(produtoDTO.nomeLongo());
        produto.setDescricao(produtoDTO.descricao());
        produto.setEstoque(produtoDTO.estoque());
        produto.setCusto(produtoDTO.valorCompra());
        produto.setValor(produtoDTO.preco());
        produto.setEstoqueMinimo(produtoDTO.estoqueMinimo());
        produto.setCategorias(new HashSet<>());
        produto.setCodigo(produtoDTO.codigo());
        produto.setCodigoBarras(produtoDTO.codigoBarras());
        // A implementação para setar a lista de categorias pode variar de acordo com
        // sua implementação de backend.
        return produto;
    }
}
