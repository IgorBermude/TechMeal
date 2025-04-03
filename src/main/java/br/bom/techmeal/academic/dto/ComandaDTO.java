package br.bom.techmeal.academic.dto;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.entity.ComandaProduto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ComandaDTO {
    private int idCompraComanda;
    private double valorTotalComanda;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Sao_Paulo")
    private Date horaEntradaComanda;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Sao_Paulo")
    private Date horaSaidaComanda;

    @JsonIgnoreProperties("comandas") // Evita referência circular no JSON
    private Cliente cliente;

    private List<ComandaProdutoDTO> comandaProdutos; // Usa DTO para evitar problemas de referência

    public ComandaDTO(Comanda comanda) {
        BeanUtils.copyProperties(comanda, this);
        this.comandaProdutos = comanda.getComandaProdutos()
                .stream()
                .map(ComandaProdutoDTO::new) // Converte para DTO
                .collect(Collectors.toList());
    }

    public ComandaDTO() {}

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

    public List<ComandaProdutoDTO> getComandaProdutos() {
        return comandaProdutos;
    }

    public void setComandaProdutos(List<ComandaProdutoDTO> comandaProdutos) {
        this.comandaProdutos = comandaProdutos;
    }
}
