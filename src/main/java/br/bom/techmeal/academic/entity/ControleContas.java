package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
@Entity
public class ControleContas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContaControleContas;

    @Temporal(value = TemporalType.DATE)
    private Date dtVencimentoControleContas;

    @Temporal(value = TemporalType.DATE)
    private Date dtPagamentoControleContas;

    @Column
    private String descricaoControleContas;

    @Column(nullable = false)
    private double valorControleContas;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false) // Define a chave estrangeira
    private Fornecedor fornecedor;
}

