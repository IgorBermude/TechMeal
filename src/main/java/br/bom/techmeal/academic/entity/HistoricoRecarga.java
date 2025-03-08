package br.bom.techmeal.academic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;
@Entity
public class HistoricoRecarga implements Serializable {
    @Temporal(value = TemporalType.DATE)
    private Date dataRecargaHistoricoRecarga;

    @Column(nullable = false)
    private double valorRecargaHistoricoRecarga;
}
