package br.giraffus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpresaDTO(String nomeEmpresa,

                         String cpf,
                         String nome,
                         String email,
                         @NotBlank @Size(min = 3, max = 1000) String senha) {
}
