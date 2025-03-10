package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
@Entity
public class HistoricoPrecoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistoricoPreco;

    @Temporal(value = TemporalType.DATE)
    private Date dataHistoricoPreco;

    @Column(nullable = false)
    private double precoAntigoHistoricoPreco;

    @Column(nullable = false)
    private double precoNovoHistoricoPreco;

    @Column(nullable = false)
    private double custoAntigoHistoricoPreco;

    @Column(nullable = false)
    private double custoNovoHistoricoPreco;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)  // Chave estrangeira para Produto
    private ProdutoEntity produto;  // Relacionamento com Produto

    // outros métodos...
}

