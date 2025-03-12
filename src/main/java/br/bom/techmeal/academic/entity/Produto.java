package br.bom.techmeal.academic.entity;

import br.bom.techmeal.academic.dto.ProdutoDTO;
import br.bom.techmeal.academic.dto.UsuarioDTO;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Entity
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduto;

    @Column(nullable = false)
    @NotNull
    private String nomeProduto;
    @NotNull
    @Column(nullable = false)
    private long codigoBarrasProduto;
    @NotNull
    @Column(nullable = false)
    private int quantProduto;
    @NotNull
    @Column(nullable = false)
    private double precoProduto;
    @NotNull
    @Column(nullable = false)
    private double valorDeCustoProduto;

    @OneToMany(mappedBy = "produto")
    private List<HistoricoPreco> historicoPrecoList;

    @ManyToMany(mappedBy = "produtoListComanda", fetch = FetchType.LAZY)
    private List<Comanda> comandaListProduto;

    public Produto(int idProduto, String nomeProduto, long codigoBarrasProduto, int quantProduto, double precoProduto, double valorDeCustoProduto, List<HistoricoPreco> historicoPrecoList, List<Comanda> comandaListProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.codigoBarrasProduto = codigoBarrasProduto;
        this.quantProduto = quantProduto;
        this.precoProduto = precoProduto;
        this.valorDeCustoProduto = valorDeCustoProduto;
        this.historicoPrecoList = historicoPrecoList;
        this.comandaListProduto = comandaListProduto;
    }

    public Produto(ProdutoDTO produto){
        BeanUtils.copyProperties(produto, this);
    }

    public Produto(){

    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public long getCodigoBarrasProduto() {
        return codigoBarrasProduto;
    }

    public void setCodigoBarrasProduto(long codigoBarrasProduto) {
        this.codigoBarrasProduto = codigoBarrasProduto;
    }

    public int getQuantProduto() {
        return quantProduto;
    }

    public void setQuantProduto(int quantProduto) {
        this.quantProduto = quantProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public double getValorDeCustoProduto() {
        return valorDeCustoProduto;
    }

    public void setValorDeCustoProduto(double valorDeCustoProduto) {
        this.valorDeCustoProduto = valorDeCustoProduto;
    }

    public List<HistoricoPreco> getHistoricoPrecoList() {
        return historicoPrecoList;
    }

    public void setHistoricoPrecoList(List<HistoricoPreco> historicoPrecoList) {
        this.historicoPrecoList = historicoPrecoList;
    }

    public List<Comanda> getComandaListProduto() {
        return comandaListProduto;
    }

    public void setComandaListProduto(List<Comanda> comandaListProduto) {
        this.comandaListProduto = comandaListProduto;
    }

    public void gerarCodigoBarras() {
        // implementação do código
    }
}

