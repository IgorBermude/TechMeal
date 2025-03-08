package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

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

    @OneToMany (mappedBy = "fornecedor")
    private List<ControleContas> controleContasListFornecedor;
}
