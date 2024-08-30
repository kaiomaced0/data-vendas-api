package br.giraffus.repository;

import br.giraffus.model.Notificacao;
import br.giraffus.model.Pagamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class NotificacaoRepository implements PanacheRepository<Notificacao> {
    public List<Notificacao> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%").list();
    }
    public List<Notificacao> findByEmpresa(Long empresa) {
        if (empresa == null)
            return null;
        return find("empresa.id = ?1 ", empresa).list();
    }
}
