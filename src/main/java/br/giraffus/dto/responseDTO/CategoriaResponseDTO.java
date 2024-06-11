package br.giraffus.dto.responseDTO;

import br.giraffus.model.Categoria;

public record CategoriaResponseDTO(
        Long id,
        String nome) {

    public CategoriaResponseDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getNome());
    }
}
