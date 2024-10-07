package br.giraffus.dto;

import java.time.LocalDate;
import java.util.List;

public record VendaDTO(
                Long idCliente,
                String observacao,
                List<ItemProdutoDTO> listaItens,
                Integer quantidadeParcelas,
                Integer intervaloParcelas,
                LocalDate dataPrimeiraParcela) {
}
