package br.giraffus.service;

import br.giraffus.dto.CidadeDTO;
import br.giraffus.dto.CidadeUpdateDTO;
import jakarta.ws.rs.core.Response;

public interface CidadeService {

    Response getAll();

    Response getId(Long id);

    Response getNome(String id);

    Response insert(CidadeDTO cidadeDTO);

    Response delete(Long id);

    Response update(CidadeUpdateDTO cidadeUpdateDTO);
}
