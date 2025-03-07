package br.bom.techmeal.academic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

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
