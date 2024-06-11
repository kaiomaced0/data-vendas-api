package br.giraffus.service;

import br.giraffus.dto.CategoriaDTO;
import br.giraffus.dto.CategoriaUpdateDTO;
import jakarta.ws.rs.core.Response;

public interface CategoriaService {

    Response getAll();

    Response getId(Long id);

    Response insert(CategoriaDTO categoriaDTO);

    Response delete(Long id);

    Response update(CategoriaUpdateDTO categoriaUpdateDTO);
}
