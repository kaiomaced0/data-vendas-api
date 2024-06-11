package br.giraffus.dto.responseDTO;

import br.giraffus.model.Fornecedor;

import java.util.List;
import java.util.stream.Collectors;

public record FornecedorResponseDTO(
        Long id,
        String nome,
        String descricao,
        String cnpj) {

    public FornecedorResponseDTO(Fornecedor fornecedor) {
        this(fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getDescricao(),
                fornecedor.getCnpj()
                 );
    }
}
