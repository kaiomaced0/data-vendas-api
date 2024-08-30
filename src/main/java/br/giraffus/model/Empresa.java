package br.giraffus.model;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Empresa extends EntityClass {

    private String nome;

    private Double metaMes;

    @OneToOne
    @JoinColumn(name = "admin")
    private Usuario admin;

    public Double getMetaMes() {
        return metaMes;
    }

    public void setMetaMes(Double metaMes) {
        this.metaMes = metaMes;
    }

    public Usuario getAdmin() {
        return admin;
    }

    public void setAdmin(Usuario admin) {
        this.admin = admin;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
