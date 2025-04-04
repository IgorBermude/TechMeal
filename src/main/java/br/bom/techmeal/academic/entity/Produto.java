package br.bom.techmeal.academic.entity;

import br.bom.techmeal.academic.dto.ProdutoDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "produto")
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

    //@Lob // Usa @Lob para armazenar grandes blobs binários
    @Column(name = "codigo_barras_imagem")
    private byte[] codigoBarrasImagemProduto;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricoPreco> historicoPrecoList;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComandaProduto> comandaProdutos;

    public Produto() {}

    public Produto(ProdutoDTO produtoDTO) {
        BeanUtils.copyProperties(produtoDTO, this);
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

    public void setCodigoBarrasImagemProduto(byte[] codigoBarrasImagemProduto) {
        this.codigoBarrasImagemProduto = codigoBarrasImagemProduto;
    }

    public List<HistoricoPreco> getHistoricoPrecoList() {
        return historicoPrecoList;
    }

    public void setHistoricoPrecoList(List<HistoricoPreco> historicoPrecoList) {
        this.historicoPrecoList = historicoPrecoList;
    }

    public List<ComandaProduto> getComandaProdutos() {
        return comandaProdutos;
    }

    public void setComandaProdutos(List<ComandaProduto> comandaProdutos) {
        this.comandaProdutos = comandaProdutos;
    }
}
