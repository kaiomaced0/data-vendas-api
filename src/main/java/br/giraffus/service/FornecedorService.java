package br.giraffus.service;

import br.giraffus.dto.FornecedorDTO;
import br.giraffus.dto.FornecedorUpdateDTO;
import br.giraffus.dto.responseDTO.FornecedorResponseDTO;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface FornecedorService {

    List<FornecedorResponseDTO> getAll();

    Response getId(Long id);

    Response insert(FornecedorDTO fornecedorDTO);

    Response update(Long id, FornecedorUpdateDTO dto);

    Response delete(@PathParam("id") Long id);
}
