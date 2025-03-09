package br.bom.techmeal.academic.service;

import br.bom.techmeal.academic.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailImpl implements UserDetails {
    private int id;
    private String name;
    private String username;
    private String password;

    public static UserDetailImpl build(Usuario usuario){
        return new UserDetailImpl(usuario.getNomeUsuario(),usuario.getIdUsuario(), usuario.getNomeUsuario(),
                new ArrayList<>()); // essa lista sera as permissoes


    }

    public UserDetailImpl(String name, int id, String username, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.id = id;
        this.username = username;
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
        return username;
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
