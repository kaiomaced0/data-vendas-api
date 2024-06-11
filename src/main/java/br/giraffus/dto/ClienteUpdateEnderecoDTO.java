package br.giraffus.dto;

public record ClienteUpdateEnderecoDTO(
        Long id,
        Long cidade,
        String endereco) {
}
