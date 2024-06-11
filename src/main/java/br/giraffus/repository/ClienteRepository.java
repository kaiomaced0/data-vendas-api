package br.giraffus.repository;

import br.giraffus.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public List<Cliente> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nomeCliente) LIKE ?1 ", "%" + nome.toUpperCase() + "%").list();
    }

}
