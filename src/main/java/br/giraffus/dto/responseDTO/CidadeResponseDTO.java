package br.giraffus.dto.responseDTO;

import br.giraffus.model.Cidade;

public record CidadeResponseDTO(
        Long id,
        String estado,
        String nome
) {
    public CidadeResponseDTO(Cidade cidade) {
        this(cidade.getId(), cidade.getEstado().getLabel(), cidade.getNome());
    }
}
