package br.giraffus.repository;

import br.giraffus.model.Pagamento;
import br.giraffus.model.Parcela;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ParcelaRepository implements PanacheRepository<Parcela> {
    public List<Parcela> findByCliente(String cliente) {
        if (cliente == null)
            return null;
        return find("UPPER(cliente) LIKE ?1 ", "%" + cliente.toUpperCase() + "%").list();
    }
}
