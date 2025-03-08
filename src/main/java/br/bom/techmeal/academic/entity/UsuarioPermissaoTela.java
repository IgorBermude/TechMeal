package br.bom.techmeal.academic.entity;

import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.ManyToAny;

public class UsuarioPermissaoTela {
    @ManyToOne
    private Tela tela;

    @ManyToOne
    private Permissao permissao;

    @ManyToOne
    private Usuario usuario;
}
