package br.giraffus.service;

import br.giraffus.dto.CategoriaDTO;
import br.giraffus.dto.CategoriaUpdateDTO;
import jakarta.ws.rs.core.Response;

public interface CategoriaService {

    Response getAll(int page, int pageSize);

    public Response getAllSize();

    Response getId(Long id);

    Response insert(CategoriaDTO categoriaDTO);

    Response delete(Long id);

    Response update(CategoriaUpdateDTO categoriaUpdateDTO);
}
