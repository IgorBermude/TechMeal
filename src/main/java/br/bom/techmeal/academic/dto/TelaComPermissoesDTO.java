package br.bom.techmeal.academic.dto;

import java.util.Set;

public class TelaComPermissoesDTO {
    private Integer idTela;
    private String tela;
    private String urlTela;
    private Set<String> permissoes;

    // Construtor vazio (default)
    public TelaComPermissoesDTO() {
    }

    // Construtor completo
    public TelaComPermissoesDTO(Integer idTela, String tela, String urlTela, Set<String> permissoes) {
        this.idTela = idTela;
        this.tela = tela;
        this.urlTela = urlTela;
        this.permissoes = permissoes;
    }

    // Getters e Setters
    public Integer getIdTela() {
        return idTela;
    }

    public void setIdTela(Integer idTela) {
        this.idTela = idTela;
    }

    public String getTela() {
        return tela;
    }

    public void setTela(String tela) {
        this.tela = tela;
    }

    public String getUrlTela() {
        return urlTela;
    }

    public void setUrlTela(String urlTela) {
        this.urlTela = urlTela;
    }

    public Set<String> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<String> permissoes) {
        this.permissoes = permissoes;
    }
}
