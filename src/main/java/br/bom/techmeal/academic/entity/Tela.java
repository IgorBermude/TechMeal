package br.bom.techmeal.academic.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference // Evita a serialização repetitiva
    private List<UsuarioPermissaoTela> usuarioPermissaoTelaListTela;
}
