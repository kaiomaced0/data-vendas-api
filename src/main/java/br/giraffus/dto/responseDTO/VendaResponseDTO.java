package br.giraffus.dto.responseDTO;

import br.giraffus.model.Venda;

import java.util.List;
import java.util.stream.Collectors;

public record VendaResponseDTO(
        Long id,
        Boolean pago,
        ClienteResponseDTO cliente,
        String observacao,
        Double valorTotal,
        List<ItemProdutoResponseDTO> listaItens) {

    public VendaResponseDTO(Venda venda) {
        this(venda.getId(),
                venda.getPago(),
                venda.getCliente() != null ? new ClienteResponseDTO(venda.getCliente()) : null,
                venda.getObservacao(),
                venda.getValorTotal(),
                venda.getItemProdutos() != null ? venda.getItemProdutos().stream()
                        .map(ItemProdutoResponseDTO::new)
                        .collect(Collectors.toList()) : null);
    }
}
