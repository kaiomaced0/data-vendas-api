package br.giraffus.dto;

public record CidadeUpdateDTO(Long id, Integer estadoId, String nome) {

    // Aqui, você pode adicionar métodos de validação ou conversão, se necessário.
    // Como por exemplo, um método para aplicar as atualizações deste DTO em uma entidade Model.

}
