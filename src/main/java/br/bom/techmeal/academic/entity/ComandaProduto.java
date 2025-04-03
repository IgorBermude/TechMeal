package br.bom.techmeal.academic.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "comanda_produto")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ComandaProduto implements Serializable {
    @EmbeddedId
    private ComandaProdutoID id = new ComandaProdutoID();

    @ManyToOne
    @MapsId("comandaId")
    @JoinColumn(name = "comanda_id")
    @JsonBackReference("comanda-comandaProduto")
    private Comanda comanda; // REMOVA @JsonBackReference

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto; // REMOVA @JsonManagedReference

    @Column(nullable = false)
    private Integer quantidade;

    public ComandaProduto() {}

    public ComandaProduto(Comanda comanda, Produto produto, Integer quantidade) {
        this.comanda = comanda;
        this.produto = produto;
        this.quantidade = (quantidade != null) ? quantidade : 1;
        this.id = new ComandaProdutoID(comanda.getIdCompraComanda(), produto.getIdProduto());
    }

    public ComandaProdutoID getId() {
        return id;
    }

    public void setId(ComandaProdutoID id) {
        this.id = id;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
        this.id.setComandaId(comanda.getIdCompraComanda());
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.id.setProdutoId(produto.getIdProduto());
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

