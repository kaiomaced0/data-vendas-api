package br.giraffus.dto;

public record ClienteUpdateDadosEmpresaDTO(
        Long id,
        String nomeEmpresa,
        String cnpj) {
}
