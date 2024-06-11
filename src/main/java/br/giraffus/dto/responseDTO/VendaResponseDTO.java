package br.giraffus.dto.responseDTO;

import br.giraffus.model.Venda;

import java.util.List;
import java.util.stream.Collectors;

public record VendaResponseDTO(
        Long id,
        ClienteResponseDTO cliente,
        String observacao,
        Double valorTotal,
        List<ItemProdutoResponseDTO> listaItens) {

    public VendaResponseDTO(Venda venda) {
        this(venda.getId(), new ClienteResponseDTO(venda.getCliente()), venda.getObservacao(), venda.getValorTotal(), venda.getItemProdutos().stream().map(ItemProdutoResponseDTO::new).collect(Collectors.toList()));
    }
}
