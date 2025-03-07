package br.bom.techmeal.academic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class Tela implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idTela;

    @Column(nullable = false)
    private String nome;

    @Column (nullable = false)
    private String url;
}
