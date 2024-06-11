package br.giraffus.dto;

import br.giraffus.model.ItemProduto;

public record ItemProdutoDTO(
        Long idProduto,
        Integer quantidade) {

    public static ItemProduto criaItemProduto(ItemProdutoDTO itemProdutoDTO) {
        ItemProduto itemProduto = new ItemProduto();
        itemProduto.setQuantidade(itemProdutoDTO.quantidade());
        return itemProduto;
    }
}