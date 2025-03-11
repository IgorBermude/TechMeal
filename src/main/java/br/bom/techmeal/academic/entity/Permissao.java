package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
@Entity
public class Permissao implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idPermisao;

    @Column(nullable = false)
    private String nomePermissao;

    @Column(nullable = false)
    private String acaoPermissao;

    @OneToMany (mappedBy = "permissao")
    private List<UsuarioPermissaoTela> usuarioPermissaoTelaListPermissao;
}
