package br.bom.techmeal.academic.entity;

import jakarta.persistence.Entity;

@Entity
public class UsuarioPermissaoTela {
    protected int idTela;
    protected int idPermissao;
    protected int idUsuario;
}
