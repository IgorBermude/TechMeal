package br.bom.techmeal.academic.dto;

import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.entity.HistoricoPreco;
import br.bom.techmeal.academic.entity.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class ProdutoDTO {
    private int idProduto;
    private String nomeProduto;
    private String codigoBarrasProduto;
    private int quantProduto;
    private double precoProduto;
    private double valorDeCustoProduto;
    private byte[] codigoBarrasImagemProduto;


    private List<HistoricoPreco> historicoPrecoList;


    private List<Comanda> comandaListProduto;

    public ProdutoDTO(Produto produto){
        BeanUtils.copyProperties(produto, this);
    }

    public ProdutoDTO() { }

    public byte[] getCodigoBarrasImagemProduto() {
        return codigoBarrasImagemProduto;
    }

    public void setCodigoBarrasImagemProduto(byte[] codigoBarrasImagemProduto) {
        this.codigoBarrasImagemProduto = codigoBarrasImagemProduto;
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
