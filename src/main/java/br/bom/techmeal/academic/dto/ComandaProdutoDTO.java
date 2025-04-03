package br.bom.techmeal.academic.dto;

import br.bom.techmeal.academic.entity.ComandaProduto;

public class ComandaProdutoDTO {
    private Integer idProduto;
    private Integer quantidade;

    public ComandaProdutoDTO() {}

    public ComandaProdutoDTO(ComandaProduto comandaProduto) {
        this.idProduto = comandaProduto.getProduto().getIdProduto();
        this.quantidade = comandaProduto.getQuantidade();
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
