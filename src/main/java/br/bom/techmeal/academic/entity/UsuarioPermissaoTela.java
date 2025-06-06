package br.bom.techmeal.academic.entity;

import br.bom.techmeal.academic.dto.UsuarioPermissaoTelaDTO;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "usuario_permissao_tela")
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

    public UsuarioPermissaoTela(UsuarioPermissaoTelaDTO usuarioPermissaoTela){
        BeanUtils.copyProperties(usuarioPermissaoTela, this);
    }

    public UsuarioPermissaoTela() {
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
