package br.bom.techmeal.academic.entity;

import br.bom.techmeal.academic.dto.ControleContasDTO;
import br.bom.techmeal.academic.dto.FornecedorDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "controle_contas")
public class ControleContas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContaControleContas;

    @Temporal(value = TemporalType.DATE)
    private Date dtVencimentoControleContas;

    @Temporal(value = TemporalType.DATE)
    private Date dtPagamentoControleContas;

    @Column
    private String descricaoControleContas;

    public String getStatusControleContas() {
        return statusControleContas;
    }

    public void setStatusControleContas(String statusControleContas) {
        this.statusControleContas = statusControleContas;
    }

    @Column(nullable = false)
    private double valorControleContas;

    @Column(nullable = false)
    private String statusControleContas;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false) // Define a chave estrangeira
    @JsonBackReference // Evita a serialização repetitiva
    private Fornecedor fornecedor;

    public ControleContas(ControleContasDTO controleContas){
        BeanUtils.copyProperties(controleContas, this);
    }

    public ControleContas(){

    }

    public int getIdContaControleContas() {
        return idContaControleContas;
    }

    public void setIdContaControleContas(int idContaControleContas) {
        this.idContaControleContas = idContaControleContas;
    }

    public String getStatusControlContas() {
        return statusControleContas;
    }

    public void setStatusControlContas(String statusControlContas) {
        this.statusControleContas = statusControlContas;
    }

    public Date getDtVencimentoControleContas() {
        return dtVencimentoControleContas;
    }

    public void setDtVencimentoControleContas(Date dtVencimentoControleContas) {
        this.dtVencimentoControleContas = dtVencimentoControleContas;
    }

    public Date getDtPagamentoControleContas() {
        return dtPagamentoControleContas;
    }

    public void setDtPagamentoControleContas(Date dtPagamentoControleContas) {
        this.dtPagamentoControleContas = dtPagamentoControleContas;
    }

    public String getDescricaoControleContas() {
        return descricaoControleContas;
    }

    public void setDescricaoControleContas(String descricaoControleContas) {
        this.descricaoControleContas = descricaoControleContas;
    }

    public double getValorControleContas() {
        return valorControleContas;
    }

    public void setValorControleContas(double valorControleContas) {
        this.valorControleContas = valorControleContas;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}

