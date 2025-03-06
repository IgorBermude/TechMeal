package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Cliente {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int idCliente;
    @Column(nullable = false)
    private String nomeCliente;
    @Column ( nullable = false , length = 5)
    private double saldoCliente;
    @Column ( nullable = false , length = 5)
    private double limiteCliente;
    @Column ( nullable = false)
    private Date dtNascCliente;
    @Column ( nullable = false)
    private Date ultimaCompraCliente;
    @Column
    private double faturaCliente;

    public void recarregarCartão(){

    }
}
