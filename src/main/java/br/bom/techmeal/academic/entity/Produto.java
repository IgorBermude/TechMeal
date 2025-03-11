package br.bom.techmeal.academic.entity;

import br.bom.techmeal.academic.dto.ProdutoDTO;
import br.bom.techmeal.academic.dto.UsuarioDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Entity
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduto;

    @Column(nullable = false)
    private String nomeProduto;

    @Column(nullable = false)
    private long codigoBarrasProduto;

    @Column(nullable = false)
    private int quantProduto;

    @Column(nullable = false)
    private double precoProduto;

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

    public void gerarCodigoBarras() {
        // implementação do código
    }
}

