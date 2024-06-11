package br.giraffus.service;

import br.giraffus.dto.NotificacaoDTO;
import br.giraffus.dto.NotificacaoUpdateDTO;
import br.giraffus.dto.responseDTO.NotificacaoResponseDTO;
import jakarta.ws.rs.core.Response;

import java.util.List;

public interface NotificacaoService {

    List<NotificacaoResponseDTO> getAll();

    Response getId(Long id);

    Response lida(Long id);

    Response insert(NotificacaoDTO notificacaoDTO);

    Response delete(Long id);

    Response update(NotificacaoUpdateDTO notificacaoUpdateDTO);
}
