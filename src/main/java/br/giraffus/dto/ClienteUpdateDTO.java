package br.giraffus.dto;

public record ClienteUpdateDTO(
        Long id,
        String nomeEmpresa,
        String cnpj,
        String nomeCliente,
        String cpfCliente,
        Long cidade,
        String endereco) {
}
