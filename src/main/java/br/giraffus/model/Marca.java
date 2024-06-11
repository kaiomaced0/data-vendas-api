package br.giraffus.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Marca extends EntityClass {

    @Column(name = "nome")
    private String nome;

    @OneToMany
    @JoinColumn(name = "lista_fornecedores_marca")
    private List<Fornecedor> fornecedores;

    @ManyToOne
    @JoinColumn(name = "empresa")
    private Empresa empresa;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    // getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
}
