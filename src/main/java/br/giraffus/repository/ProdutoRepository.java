package br.giraffus.repository;

import br.giraffus.model.Categoria;
import br.giraffus.model.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {
    public List<Produto> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%").list();
    }
    public List<Produto> findByEmpresa(Long empresa) {
        if (empresa == null)
            return null;
        return find("empresa.id = ?1 ", empresa).list();
    }

    public void update(Produto p){
        getEntityManager().merge(p);
    }
}
