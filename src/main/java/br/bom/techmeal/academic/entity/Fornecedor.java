package br.bom.techmeal.academic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class Fornecedor implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idFornecedor;

    @Column(nullable = false)
    private String cnpjFornecedor;

    @Column(nullable = false)
    private String nomeSocialFornecedor;

    @Column(nullable = false)
    private String celularFornecedor;

    @Column(nullable = false)
    private String emailFornecedor;

    @Column(nullable = false)
    private String chavePixFornecedor;
}
