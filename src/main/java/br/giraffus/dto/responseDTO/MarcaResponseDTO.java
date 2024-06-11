package br.giraffus.dto.responseDTO;

import br.giraffus.model.Marca;

import java.util.List;
import java.util.stream.Collectors;

public record MarcaResponseDTO(Long id, String nome, List<FornecedorResponseDTO> fornecedores) {

    public MarcaResponseDTO(Marca marca) {
        this(marca.getId(), marca.getNome(),
                marca.getFornecedores().stream()
                        .map(fornecedor -> new FornecedorResponseDTO(fornecedor))
                        .collect(Collectors.toList()));
    }
}
