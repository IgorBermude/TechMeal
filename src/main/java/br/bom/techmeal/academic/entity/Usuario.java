package br.bom.techmeal.academic.entity;

import br.bom.techmeal.academic.dto.UsuarioDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

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

    @Column(nullable = false)
    private String login;

    @Column (nullable = false)
    private String senhaUsuario;

    @OneToMany (mappedBy = "usuario")
    private List<UsuarioPermissaoTela> usuarioPermissaoTelaListUsuario;


    public Usuario(int idUsuario, List<UsuarioPermissaoTela> usuarioPermissaoTelaListUsuario, String senhaUsuario, String login, String nomeUsuario, String emailUsuario, String telefoneUsuario) {
        this.idUsuario = idUsuario;
        this.usuarioPermissaoTelaListUsuario = usuarioPermissaoTelaListUsuario;
        this.senhaUsuario = senhaUsuario;
        this.login = login;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.telefoneUsuario = telefoneUsuario;
    }

    public Usuario(UsuarioDTO usuario){
        BeanUtils.copyProperties(usuario, this);
    }

    public Usuario(){

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public List<UsuarioPermissaoTela> getUsuarioPermissaoTelaListUsuario() {
        return usuarioPermissaoTelaListUsuario;
    }

    public void setUsuarioPermissaoTelaListUsuario(List<UsuarioPermissaoTela> usuarioPermissaoTelaListUsuario) {
        this.usuarioPermissaoTelaListUsuario = usuarioPermissaoTelaListUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public void login(){

    }

    public void logout(){

    }
}
