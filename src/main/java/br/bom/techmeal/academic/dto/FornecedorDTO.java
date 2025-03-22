package br.bom.techmeal.academic.dto;

import br.bom.techmeal.academic.entity.ControleContas;
import br.bom.techmeal.academic.entity.Fornecedor;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class FornecedorDTO {
    private int idFornecedor;
    private String cnpjFornecedor;
    private String nomeSocialFornecedor;
    private String celularFornecedor;
    private String emailFornecedor;
    private String chavePixFornecedor;
    private List<ControleContas> controleContasListFornecedor;

    public FornecedorDTO(Fornecedor fornecedor){
        BeanUtils.copyProperties(fornecedor, this);
    }

    public FornecedorDTO(){

    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getCnpjFornecedor() {
        return cnpjFornecedor;
    }

    public void setCnpjFornecedor(String cnpjFornecedor) {
        this.cnpjFornecedor = cnpjFornecedor;
    }

    public String getNomeSocialFornecedor() {
        return nomeSocialFornecedor;
    }

    public void setNomeSocialFornecedor(String nomeSocialFornecedor) {
        this.nomeSocialFornecedor = nomeSocialFornecedor;
    }

    public String getCelularFornecedor() {
        return celularFornecedor;
    }

    public void setCelularFornecedor(String celularFornecedor) {
        this.celularFornecedor = celularFornecedor;
    }

    public String getEmailFornecedor() {
        return emailFornecedor;
    }

    public void setEmailFornecedor(String emailFornecedor) {
        this.emailFornecedor = emailFornecedor;
    }

    public String getChavePixFornecedor() {
        return chavePixFornecedor;
    }

    public void setChavePixFornecedor(String chavePixFornecedor) {
        this.chavePixFornecedor = chavePixFornecedor;
    }

    public List<ControleContas> getControleContasListFornecedor() {
        return controleContasListFornecedor;
    }

    public void setControleContasListFornecedor(List<ControleContas> controleContasListFornecedor) {
        this.controleContasListFornecedor = controleContasListFornecedor;
    }
}
