package br.giraffus.dto;
import br.giraffus.model.Cliente;

public record ClienteDTO(
        String nomeEmpresa,
        String cnpj,
        String nomeCliente,
        String cpfCliente,
        Long idCidade,
        String endereco) {

    public static Cliente criaCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();

        if (clienteDTO.nomeCliente() != null)
            cliente.setNomeCliente(clienteDTO.nomeCliente());
        if (clienteDTO.nomeEmpresa() != null)
            cliente.setNomeEmpresa(clienteDTO.nomeEmpresa());
        if (clienteDTO.cnpj() != null)
            cliente.setCnpj(clienteDTO.cnpj());
        if (clienteDTO.cpfCliente() != null)
            cliente.setCpfCliente(clienteDTO.cpfCliente());
        if (clienteDTO.endereco() != null)
            cliente.setEndereco(clienteDTO.endereco());

        return cliente;
    }
}
