package br.giraffus.dto;

public record NotificacaoUpdateDTO(
        Long id,
        String titulo, String descricao, Long tipoNotificacao) {


}
