package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

@Entity
public class UsuarioPermissaoTelaEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int idPermissaoTela;
    @ManyToOne
    private TelaEntity tela;

    @ManyToOne
    private PermissaoEntity permissao;

    @ManyToOne
    private UsuarioEntity usuario;
}
