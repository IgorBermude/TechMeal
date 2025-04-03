package br.bom.techmeal.academic.dto;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.entity.Produto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

public class ComandaDTO {
    private int idCompraComanda;
    private double valorTotalComanda;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Sao_Paulo")
    private Date horaEntradaComanda;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Sao_Paulo")
    private Date horaSaidaComanda;

    private Cliente cliente;

    private List<ProdutoQuantidadeDTO> produtoListComanda;

    public ComandaDTO(Comanda comanda){
        BeanUtils.copyProperties(comanda, this);
    }

    public ComandaDTO(){ }

    public int getIdCompraComanda() {
        return idCompraComanda;
    }

    public void setIdCompraComanda(int idCompraComanda) {
        this.idCompraComanda = idCompraComanda;
    }

    public double getValorTotalComanda() {
        return valorTotalComanda;
    }

    public void setValorTotalComanda(double valorTotalComanda) {
        this.valorTotalComanda = valorTotalComanda;
    }

    public Date getHoraEntradaComanda() {
        return horaEntradaComanda;
    }

    public void setHoraEntradaComanda(Date horaEntradaComanda) {
        this.horaEntradaComanda = horaEntradaComanda;
    }

    public Date getHoraSaidaComanda() {
        return horaSaidaComanda;
    }

    public void setHoraSaidaComanda(Date horaSaidaComanda) {
        this.horaSaidaComanda = horaSaidaComanda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ProdutoQuantidadeDTO> getProdutoListComanda() {
        return produtoListComanda;
    }

    public void setProdutoListComanda(List<ProdutoQuantidadeDTO> produtoListComanda) {
        this.produtoListComanda = produtoListComanda;
    }
}
