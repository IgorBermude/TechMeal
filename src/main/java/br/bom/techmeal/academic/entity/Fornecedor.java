package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
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
