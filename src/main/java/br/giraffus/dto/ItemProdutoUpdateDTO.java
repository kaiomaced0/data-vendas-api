package br.giraffus.dto;


public record ItemProdutoUpdateDTO(
        Long id,
        Long  idproduto,
        Integer quantidade) {}
