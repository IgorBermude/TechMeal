package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

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

    public void gerarCodigoBarras() {
        // implementação do código
    }
}

