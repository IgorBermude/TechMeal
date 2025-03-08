package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Tela implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idTela;

    @Column(nullable = false)
    private String nome;

    @Column (nullable = false)
    private String url;

    @OneToMany(mappedBy = "tela")
    private List<UsuarioPermissaoTela> usuarioPermissaoTelaListTela;
}
