package br.giraffus.dto.responseDTO;

import br.giraffus.model.Notificacao;

public record NotificacaoResponseDTO(
        Long id,
        String titulo) {

    public NotificacaoResponseDTO(Notificacao notificacao) {
        this(notificacao.getId(), notificacao.getTitulo());
    }
}
