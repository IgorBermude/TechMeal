package br.bom.techmeal.academic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class Permissao implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idPermisao;

    @Column(nullable = false)
    private String nomePermissao;

    @Column(nullable = false)
    private String acaoPermissao;
}
