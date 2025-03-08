package br.bom.techmeal.academic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;
@Entity
public class HistoricoPreco implements Serializable {
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
}
