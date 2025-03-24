package br.bom.techmeal.academic.entity;

import br.bom.techmeal.academic.dto.FornecedorDTO;
import br.bom.techmeal.academic.dto.ProdutoDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Entity
public class Fornecedor implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int idFornecedor;

    @Column(nullable = false)
    private String cnpjFornecedor;

    @Column(nullable = false)
    private String nomeSocialFornecedor;

    @Column(nullable = false)
    private String celularFornecedor;

    @Column(nullable = false)
    private String emailFornecedor;

    @Column(nullable = false)
    private String chavePixFornecedor;

    @OneToMany (mappedBy = "fornecedor")
    @JsonBackReference // Evita a serialização repetitiva
    private List<ControleContas> controleContasListFornecedor;

    public Fornecedor(FornecedorDTO fornecedor){
        BeanUtils.copyProperties(fornecedor, this);
    }

    public Fornecedor(){

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
