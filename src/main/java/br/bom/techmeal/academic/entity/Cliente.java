package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Cliente implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int idCliente;
    @Column(nullable = false)
    private String nomeCliente;
    @Column ( nullable = false , length = 5)
    private double saldoCliente;
    @Column ( nullable = false , length = 5)
    private double limiteCliente;
    @Temporal(value = TemporalType.DATE)
    private Date dtNascCliente;
    @Temporal(value = TemporalType.DATE)
    private Date ultimaCompraCliente;
    @Column
    private double faturaCliente;

    @OneToMany(mappedBy ="cliente")
    private List<Comanda> comandas;

    @OneToMany(mappedBy = "cliente")
    private List<HistoricoRecarga> historicoRecarga;

    public void recarregarCartão(){

    }
}
