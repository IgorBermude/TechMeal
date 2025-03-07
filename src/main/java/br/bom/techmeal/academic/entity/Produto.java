package br.bom.techmeal.academic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

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
