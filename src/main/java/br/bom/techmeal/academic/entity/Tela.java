package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
public class Tela implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idTela;

    @Column(nullable = false)
    private String nome;

    @Column (nullable = false)
    private String url;
}
