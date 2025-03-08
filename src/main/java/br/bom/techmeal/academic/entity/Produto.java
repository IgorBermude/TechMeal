package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Produto implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idProduto;

    @Column(nullable = false)
    private String nomeProduto;

    @Column(nullable = false)
    private long codigoBarrasProduto;

    @Column(nullable = false)
    private int quantProduto;

    @Column(nullable = false)
    private double precoProduto;

    @Column(nullable = false)
    private double valorDeCustoProduto;

    public void gerarCodigoBarras(){

    }
}
