package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Usuario implements Serializable{
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column (nullable = false)
    private String emailUsuario;

    @Column (nullable = false)
    private String telefoneUsuario;

    @Column (nullable = false)
    private String nomeUsuario;

    @Column (nullable = false)
    private String senhaUsuario;

    @OneToMany (mappedBy = "Usuario")
    private List<UsuarioPermissaoTela> usuarioPermissaoTelaListUsuario;

    public void login(){

    }

    public void logout(){

    }
}
