package br.giraffus.service;

import br.giraffus.dto.MarcaDTO;
import br.giraffus.dto.MarcaUpdateDTO;
import br.giraffus.dto.responseDTO.MarcaResponseDTO;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface MarcaService {

    List<MarcaResponseDTO> getAll();

    Response getId(Long id);

    Response insert(MarcaDTO marcaDTO);

    Response delete(@PathParam("id") Long id);

    Response update(MarcaUpdateDTO marcaUpdateDTO);
}
