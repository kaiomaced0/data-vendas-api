package br.giraffus.repository;

import br.giraffus.model.ItemProduto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ItemProdutoRepository implements PanacheRepository<ItemProduto> {

    public List<ItemProduto> findByProdutoNome(String nome) {
        if (nome == null) {
            return null;
        }
        return find("UPPER(produto.nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%").list();
    }
}
