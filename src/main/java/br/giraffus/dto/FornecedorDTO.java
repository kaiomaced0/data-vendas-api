package br.giraffus.dto;

import br.giraffus.model.Fornecedor;

import java.util.HashSet;
import java.util.List;

public record FornecedorDTO(
        String nome,
        String descricao,
        String cnpj) {

    public static Fornecedor criaFornecedor(FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorDTO.nome());
        fornecedor.setDescricao(fornecedorDTO.descricao());
        fornecedor.setCnpj(fornecedorDTO.cnpj());

        return fornecedor;
    }
}
