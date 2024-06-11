package br.giraffus.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pagamento extends EntityClass{

    @ManyToOne
    @JoinColumn(name = "venda")
    private Venda venda;

    private Integer quantidadeParcelas;

    private Integer intervaloParcelas;

    private LocalDate dataPrimeiraParcela;

    @OneToMany
    @JoinColumn(name = "pagamento")
    private List<Parcela> parcelas;

    private Double valor;

    private Boolean pago;

    private LocalDate dataVencimento;

    @ManyToOne
    @JoinColumn(name = "empresa")
    private Empresa empresa;

    public Integer getIntervaloParcelas() {
        return intervaloParcelas;
    }

    public void setIntervaloParcelas(Integer intervaloParcelas) {
        this.intervaloParcelas = intervaloParcelas;
    }

    public LocalDate getDataPrimeiraParcela() {
        return dataPrimeiraParcela;
    }

    public void setDataPrimeiraParcela(LocalDate dataPrimeiraParcela) {
        this.dataPrimeiraParcela = dataPrimeiraParcela;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public List<Parcela> getParcelas() {
        return parcelas;
    }

    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
