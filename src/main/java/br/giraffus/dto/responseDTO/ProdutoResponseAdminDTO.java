package br.giraffus.dto.responseDTO;

import br.giraffus.model.Produto;

import java.util.List;
import java.util.stream.Collectors;

public record ProdutoResponseAdminDTO(
        Long id,
        String nome,
        String nomeLongo,
        String descricao,
        Integer estoque,
        Double valorCompra,
        Double preco,
        Long idFornecedor,
        Long idMarca,
        List<CategoriaResponseDTO> categorias,
        Integer estoqueMinimo,
        String codigo,
        String codigoBarras) {

    public ProdutoResponseAdminDTO(Produto produto) {
        this(produto.getId(),
                produto.getNome(),
                produto.getNomeLongo(),
                produto.getDescricao(),
                produto.getEstoque(),
                produto.getCusto(),
                produto.getValor(),
                (produto.getFornecedor() != null) ? produto.getFornecedor().getId() : null,
                (produto.getMarca() != null) ? produto.getMarca().getId() : null,
                (produto.getCategorias() != null) ? produto.getCategorias().stream()
                        .map(CategoriaResponseDTO::new)
                        .collect(Collectors.toList()) : null,
                produto.getEstoqueMinimo(),
                produto.getCodigo(),
                produto.getCodigoBarras());
    }
}
