package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ManyToAny;
@Entity
public class UsuarioPermissaoTela {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int idPermissaoTela;
    @ManyToOne
    private Tela tela;

    @ManyToOne
    private Permissao permissao;

    @ManyToOne
    private Usuario usuario;
}
