package br.bom.techmeal.academic.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "comanda_produto")
public class ComandaProduto implements Serializable {

    @EmbeddedId
    private ComandaProdutoID id = new ComandaProdutoID();

    @ManyToOne
    @MapsId("comandaId") // Mapeia a chave primária composta
    @JoinColumn(name = "comanda_id")
    @JsonBackReference
    private Comanda comanda;

    @ManyToOne
    @MapsId("produtoId") // Mapeia a chave primária composta
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    public ComandaProduto() {}

    public ComandaProduto(Comanda comanda, Produto produto, Integer quantidade) {
        this.comanda = comanda;
        this.produto = produto;
        this.quantidade = (quantidade != null) ? quantidade : 1; // Define 1 como padrão
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComandaProduto that = (ComandaProduto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
