package br.giraffus.repository;

import br.giraffus.model.Fornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor> {
    public List<Fornecedor> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%").list();
    }
}
