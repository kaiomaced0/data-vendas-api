package br.giraffus.service;

import br.giraffus.dto.VendaDTO;
import br.giraffus.dto.responseDTO.VendaResponseDTO;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface VendaService {

    public Response getAll(int page, int pageSize);

    public Response getAllSize();

    Response getId(Long id);

    Response insert(VendaDTO VendaDTO);

//    Response update(VendaUpdateDTO VendaUpdateDTO);

    Response delete(@PathParam("id") Long id);
}
