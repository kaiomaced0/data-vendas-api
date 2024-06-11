package br.giraffus.service;

import br.giraffus.dto.ItemProdutoDTO;
import br.giraffus.dto.ItemProdutoUpdateDTO;
import br.giraffus.dto.responseDTO.ItemProdutoResponseDTO;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface ItemProdutoService {

    List<ItemProdutoResponseDTO> getAll();

    Response getId(Long id);

    Response insert(ItemProdutoDTO itemProdutoDTO);

    Response delete(Long id);

    Response update(ItemProdutoUpdateDTO itemProdutoUpdateDTO);
}
