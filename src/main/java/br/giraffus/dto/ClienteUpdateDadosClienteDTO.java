package br.giraffus.dto;

public record ClienteUpdateDadosClienteDTO(
        Long id,
        String nomeCliente,
        String cpfCliente) {
}
