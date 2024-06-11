package br.giraffus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateSenhaDTO(
        @NotBlank
        String login,
        @Size(min = 3, max = 1000)
        @NotBlank
        String senhaAnterior,

        @Size(min = 3, max = 1000)
        @NotBlank
        String novaSenha) {
}
