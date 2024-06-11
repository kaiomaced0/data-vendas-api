package br.giraffus.dto.responseDTO;

import br.giraffus.model.ItemProduto;

public record ItemProdutoResponseDTO(
        Long id,
        ProdutoResponseDTO produto,
        Integer quantidade,
        Double preco
) {
    public ItemProdutoResponseDTO(ItemProduto itemProduto) {
        this(itemProduto.getId(),
                new ProdutoResponseDTO(itemProduto.getProduto()),
                itemProduto.getQuantidade(),
                itemProduto.getPreco());
    }
}
