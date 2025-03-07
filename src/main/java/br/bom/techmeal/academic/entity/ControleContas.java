package br.bom.techmeal.academic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;
@Entity
public class ControleContas implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int idContaControleContas;

    private Date dtVencimentoControleContas;
    private Date dtPagamentoControleContas;
    private String descricaoControleContas;
    private double valorControleContas;
}
