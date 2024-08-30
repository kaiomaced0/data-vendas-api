package br.giraffus.dto.responseDTO;

import br.giraffus.model.Pagamento;

import java.time.LocalDate;

public record PagamentoResponseDTO(
        Long id,
        Long idCliente,
        String nomeCliente,
        Long idVenda,
        Integer quantidadeParcelas,
        LocalDate dataPrimeiraParcela,
        Double valor,
        Boolean pago

) {
    public PagamentoResponseDTO(Pagamento p){
        this(p.getId(),
                p.getVenda().getCliente().getId(),
                p.getVenda().getCliente().getNomeCliente(),
                p.getVenda().getId(),
                p.getQuantidadeParcelas(),
                p.getDataPrimeiraParcela(),
                p.getValor(),
                p.getPago());
    }
}
