package br.bom.techmeal.academic.dto;

import br.bom.techmeal.academic.entity.UsuarioEntity;
import br.bom.techmeal.academic.entity.UsuarioPermissaoTelaEntity;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class UsuarioDTO {

    private int idUsuario;
    private String emailUsuario;
    private String telefoneUsuario;
    private String nomeUsuario;
    private String login;
    private String senhaUsuario;
    private List<UsuarioPermissaoTelaEntity> usuarioPermissaoTelaListUsuario;

    public UsuarioDTO(UsuarioEntity usuario){
        BeanUtils.copyProperties(usuario, this);
    }

    public UsuarioDTO(){

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public List<UsuarioPermissaoTelaEntity> getUsuarioPermissaoTelaListUsuario() {
        return usuarioPermissaoTelaListUsuario;
    }

    public void setUsuarioPermissaoTelaListUsuario(List<UsuarioPermissaoTelaEntity> usuarioPermissaoTelaListUsuario) {
        this.usuarioPermissaoTelaListUsuario = usuarioPermissaoTelaListUsuario;
    }
}
