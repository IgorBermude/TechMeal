package br.bom.techmeal.academic.entity;



import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ComandaProdutoID implements Serializable {

    private int comandaId;
    private int produtoId;

    public ComandaProdutoID() {}

    public ComandaProdutoID(int comandaId, int produtoId) {
        this.comandaId = comandaId;
        this.produtoId = produtoId;
    }

    public int getComandaId() {
        return comandaId;
    }

    public void setComandaId(int comandaId) {
        this.comandaId = comandaId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComandaProdutoID that = (ComandaProdutoID) o;
        return comandaId == that.comandaId && produtoId == that.produtoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(comandaId, produtoId);
    }
}

