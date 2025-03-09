package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
@Entity

public class HistoricoRecarga implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int idHistoricoRecarga;
    @Temporal(value = TemporalType.DATE)
    private Date dataRecargaHistoricoRecarga;

    @Column(nullable = false)
    private double valorRecargaHistoricoRecarga;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
}
