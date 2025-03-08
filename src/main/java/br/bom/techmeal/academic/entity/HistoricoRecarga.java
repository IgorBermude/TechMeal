package br.bom.techmeal.academic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

public class HistoricoRecarga implements Serializable {
    @Temporal(value = TemporalType.DATE)
    private Date dataRecargaHistoricoRecarga;

    @Column(nullable = false)
    private double valorRecargaHistoricoRecarga;

    @ManyToOne
    private HistoricoRecarga historicoRecarga;
}
