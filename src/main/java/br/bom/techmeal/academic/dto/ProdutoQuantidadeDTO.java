package br.bom.techmeal.academic.dto;

public class ProdutoQuantidadeDTO {
    private Integer idProduto;
    private Integer quantidade;

    public ProdutoQuantidadeDTO(Integer quantidade, Integer idProduto) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
    }

    public ProdutoQuantidadeDTO() {
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

