package br.bom.techmeal.academic.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "permissao")
public class Permissao implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idPermisao;

    @Column(nullable = false)
    private String nomePermissao;

    @Column(nullable = false)
    private String acaoPermissao;

    @OneToMany (mappedBy = "permissao")
    @JsonBackReference // Evita a serialização repetitiva
    private List<UsuarioPermissaoTela> usuarioPermissaoTelaListPermissao;
}
