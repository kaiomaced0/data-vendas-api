package br.giraffus.dto;

import java.util.List;

public record FornecedorUpdateDTO(
        String nome,
        String descricao,
        String cnpj
) {}
