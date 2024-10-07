package br.giraffus.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto extends EntityClass {

    
    @Size(max = 180)
    @Column(name = "codigo_barras")
    private String codigoBarras;
    
    @Size(max = 100)
    @Column(name = "codigo")
    private String codigo;

    @Size(max = 40)
    @Column(name = "nome")
    private String nome;
    
    @Size(max = 220)
    @Column(name = "nome_longo")
    private String nomeLongo;

    @Size(max = 5000)
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "estoque")
    private Integer estoque;

    @Column(name = "valor_compra")
    private Double custo;

    @Column(name = "valor_venda")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @Column(name = "estoque_minimo")
    private Integer estoqueMinimo;

    @ManyToOne
    @JoinColumn(name = "marca_produto")
    private Marca marca;

    @ManyToMany
    @CollectionTable(name = "lista_categorias")
    private Set<Categoria> categorias;

    @ManyToOne
    @JoinColumn(name = "empresa")
    private Empresa empresa;
    

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNomeLongo() {
        return nomeLongo;
    }

    public void setNomeLongo(String nomeLongo) {
        this.nomeLongo = nomeLongo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }
}
