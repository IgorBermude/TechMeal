package br.bom.techmeal.academic.dto;

import br.bom.techmeal.academic.entity.ControleContas;
import br.bom.techmeal.academic.entity.Fornecedor;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;

import java.util.Calendar;
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

    // Construtor para conversão da entidade (GET)
    public ControleContasDTO(ControleContas controleContas) {
        BeanUtils.copyProperties(controleContas, this);
        // Sobrescreve a data para evitar ajuste duplicado
        this.dtVencimentoControleContas = controleContas.getDtVencimentoControleContas();
        if (this.statusControleContas == null) {
            this.statusControleContas = "Não Paga";
        }
    }

    public ControleContasDTO() {
        this.statusControleContas = "Não Paga";
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

    // Setter para entrada de dados (POST)
    public void setDtVencimentoControleContas(Date dtVencimentoControleContas) {
        if (dtVencimentoControleContas != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dtVencimentoControleContas);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            // Adiciona 1 dia para compensar (necessário apenas para entrada, se assim você configurou)
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            this.dtVencimentoControleContas = new java.sql.Date(calendar.getTimeInMillis());
        } else {
            this.dtVencimentoControleContas = null;
        }
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
