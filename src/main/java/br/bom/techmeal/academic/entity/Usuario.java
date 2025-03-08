package br.bom.techmeal.academic.entity;

import jakarta.persistence.*;

import java.io.Serializable;
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

    public void login(){

    }

    public void logout(){

    }
}
