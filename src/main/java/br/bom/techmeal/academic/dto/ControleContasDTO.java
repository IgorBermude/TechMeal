package br.bom.techmeal.academic.dto;

import br.bom.techmeal.academic.entity.ControleContas;
import br.bom.techmeal.academic.entity.Fornecedor;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ControleContasDTO {
    private int idContaControleContas;
    private Date dtVencimentoControleContas;
    private Date dtPagamentoControleContas;
    private String descricaoControleContas;
    private double valorControleContas;
    private Fornecedor fornecedor;
    private String statusControleContas;

    public ControleContasDTO(ControleContas controleContas) {
        BeanUtils.copyProperties(controleContas, this);
        if (this.statusControleContas == null) {
            this.statusControleContas = "PENDENTE"; // Define um valor padrão se estiver nulo
        }
    }

    public ControleContasDTO() {
        this.statusControleContas = "PENDENTE"; // Define um valor padrão no construtor vazio
    }

    public int getIdContaControleContas() {
        return idContaControleContas;
    }

    public void setIdContaControleContas(int idContaControleContas) {
        this.idContaControleContas = idContaControleContas;
    }

    public String getStatusControleContas() {
        return statusControleContas;
    }

    public void setStatusControleContas(String statusControleContas) {
        this.statusControleContas = statusControleContas;
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
