package br.giraffus.dto.responseDTO;

import br.giraffus.model.Marca;

import java.util.List;
import java.util.stream.Collectors;

public record MarcaResponseDTO(Long id, String nome) {

    public MarcaResponseDTO(Marca marca) {
        this(marca.getId(), marca.getNome());
    }
}
