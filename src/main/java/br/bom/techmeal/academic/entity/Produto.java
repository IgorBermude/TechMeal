package br.bom.techmeal.academic.entity;

import br.bom.techmeal.academic.dto.ProdutoDTO;
import br.bom.techmeal.academic.dto.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idProduto")
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduto;

    @Column(nullable = false)
    private String nomeProduto;

    @Column(nullable = false, unique = true)
    private String codigoBarrasProduto;

    @Column(nullable = false)
    private int quantProduto;

    @Column(nullable = false)
    private double precoProduto;

    @Column(nullable = false)
    private double valorDeCustoProduto;

    //@Lob
    //@JsonIgnore
    @Column(name = "codigo_barras_imagem")
    private byte[] codigoBarrasImagemProduto;

    @OneToMany(mappedBy = "produto")

    private List<HistoricoPreco> historicoPrecoList;

    @ManyToMany(mappedBy = "produtoListComanda", fetch = FetchType.LAZY)

    private List<Comanda> comandaListProduto;

    public Produto(int idProduto, String nomeProduto, String codigoBarrasProduto, int quantProduto, double precoProduto, double valorDeCustoProduto, List<HistoricoPreco> historicoPrecoList, List<Comanda> comandaListProduto, byte[] codigoBarrasImagemProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.codigoBarrasProduto = codigoBarrasProduto;
        this.quantProduto = quantProduto;
        this.precoProduto = precoProduto;
        this.valorDeCustoProduto = valorDeCustoProduto;
        this.historicoPrecoList = historicoPrecoList;
        this.comandaListProduto = comandaListProduto;
        this.codigoBarrasImagemProduto = codigoBarrasImagemProduto;
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

    public String getCodigoBarrasProduto() {
        return codigoBarrasProduto;
    }

    public void setCodigoBarrasProduto(String codigoBarrasProduto) {
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

    public byte[] getCodigoBarrasImagemProduto() {
        return codigoBarrasImagemProduto;
    }

    public void setCodigoBarrasImagemProduto(byte[] codigoBarrasImagem) {
        this.codigoBarrasImagemProduto = codigoBarrasImagem;
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
}
