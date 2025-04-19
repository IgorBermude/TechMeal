package br.bom.techmeal.academic.dto;

import br.bom.techmeal.academic.entity.Permissao;
import br.bom.techmeal.academic.entity.Tela;
import br.bom.techmeal.academic.entity.Usuario;
import br.bom.techmeal.academic.entity.UsuarioPermissaoTela;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.BeanUtils;

public class UsuarioPermissaoTelaDTO {
    private int idPermissaoTela;
    private Tela tela;
    private Permissao permissao;
    private Usuario usuario;

    public UsuarioPermissaoTelaDTO(UsuarioPermissaoTela usuarioPermissaoTela){
        BeanUtils.copyProperties(usuarioPermissaoTela, this);
    }

    public UsuarioPermissaoTelaDTO(){

    }

    public int getIdPermissaoTela() {
        return idPermissaoTela;
    }

    public void setIdPermissaoTela(int idPermissaoTela) {
        this.idPermissaoTela = idPermissaoTela;
    }

    public Tela getTela() {
        return tela;
    }

    public void setTela(Tela tela) {
        this.tela = tela;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
