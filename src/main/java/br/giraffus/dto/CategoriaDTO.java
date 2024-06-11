package br.giraffus.dto;
import br.giraffus.model.Categoria;

public record CategoriaDTO(
        String nome) {

    public static Categoria criaCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.nome());
        return categoria;
    }
}
