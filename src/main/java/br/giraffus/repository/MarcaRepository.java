package br.giraffus.repository;

import br.giraffus.model.Fornecedor;
import br.giraffus.model.Marca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {
    public List<Marca> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%").list();
    }
    public List<Marca> findByEmpresa(Long empresa) {
        if (empresa == null)
            return null;
        return find("empresa.id = ?1 ", empresa).list();
    }
}
