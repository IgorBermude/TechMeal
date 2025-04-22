package br.bom.techmeal.academic.dto;

import java.util.Set;

public class TelaComPermissoesDTO {
    private String tela;
    private Set<String> permissoes;

    public TelaComPermissoesDTO(String tela, Set<String> permissoes) {
        this.tela = tela;
        this.permissoes = permissoes;
    }

    public String getTela() {
        return tela;
    }

    public void setTela(String tela) {
        this.tela = tela;
    }

    public Set<String> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<String> permissoes) {
        this.permissoes = permissoes;
    }
// Getters e Setters
}

