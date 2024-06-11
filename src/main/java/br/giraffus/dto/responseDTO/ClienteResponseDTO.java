package br.giraffus.dto.responseDTO;

import br.giraffus.model.Cliente;

public record ClienteResponseDTO(
        Long id,
        String nomeEmpresa,
        String cnpj,
        String nomeCliente,
        String cpfCliente,
        CidadeResponseDTO cidade,
        String endereco
) {
    public ClienteResponseDTO(Cliente cliente) {
        this(cliente.getId(),
                cliente.getNomeEmpresa(),
                cliente.getCnpj(),
                cliente.getNomeCliente(),
                cliente.getCpfCliente(),
                new CidadeResponseDTO(cliente.getCidade()),
                cliente.getEndereco());
    }
}
