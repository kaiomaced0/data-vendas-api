package br.giraffus.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Venda extends EntityClass {

    @JoinColumn(name = "itemproduto_venda")
    @OneToMany
    private List<ItemProduto> itemProdutos;

    @JoinColumn
    @ManyToOne
    private Cliente cliente;

    private String observacao;

    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "empresa")
    private Empresa empresa;

    private Boolean pago;

    private Boolean finalizada;
    

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<ItemProduto> getItemProdutos() {
        return itemProdutos;
    }
    public void setItemProdutos(List<ItemProduto> itemProdutos) {
        this.itemProdutos = itemProdutos;
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public Double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Boolean getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(Boolean finalizada) {
        this.finalizada = finalizada;
    }

    

}
