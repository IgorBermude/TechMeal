package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.entity.UsuarioEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailImpl implements UserDetails {
    private int id;
    private String name;
    private String login;
    private String password;

    public static UserDetailImpl build(UsuarioEntity usuario){
        return new UserDetailImpl(usuario.getNomeUsuario(),usuario.getIdUsuario(), usuario.getLogin(),
                new ArrayList<>()); // essa lista sera as permissoes


    }

    public UserDetailImpl(String name, int id, String username, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.id = id;
        this.login = login;
        this.authorities = authorities;
    }

    private Collection <? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
