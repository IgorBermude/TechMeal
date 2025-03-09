package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Comanda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompraComanda;

    @Column(name = "valorTotal", nullable = false)
    private int valorTotalComanda;

    @Temporal(value = TemporalType.DATE)
    private Date horaEntradaComanda;

    @Temporal(value = TemporalType.DATE)
    private Date horaSaidaComanda;

    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "comanda_produto",
            joinColumns = @JoinColumn(name = "comanda_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtoListComanda;
}

