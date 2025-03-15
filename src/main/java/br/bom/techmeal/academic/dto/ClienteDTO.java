package br.bom.techmeal.academic.dto;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.entity.HistoricoRecarga;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

public class ClienteDTO {
    private int idCliente;
    private String nomeCliente;
    private double saldoCliente;
    private double limiteCliente;
    private Date dtNascCliente;
    private Date ultimaCompraCliente;
    private double faturaCliente;
    private List<Comanda> comandas;
    private List<HistoricoRecarga> historicoRecarga;

    public ClienteDTO(Cliente cliente){
        BeanUtils.copyProperties(cliente, this);
    }

    public ClienteDTO(){

    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getSaldoCliente() {
        return saldoCliente;
    }

    public void setSaldoCliente(double saldoCliente) {
        this.saldoCliente = saldoCliente;
    }

    public double getLimiteCliente() {
        return limiteCliente;
    }

    public void setLimiteCliente(double limiteCliente) {
        this.limiteCliente = limiteCliente;
    }

    public Date getDtNascCliente() {
        return dtNascCliente;
    }

    public void setDtNascCliente(Date dtNascCliente) {
        this.dtNascCliente = dtNascCliente;
    }

    public Date getUltimaCompraCliente() {
        return ultimaCompraCliente;
    }

    public void setUltimaCompraCliente(Date ultimaCompraCliente) {
        this.ultimaCompraCliente = ultimaCompraCliente;
    }

    public double getFaturaCliente() {
        return faturaCliente;
    }

    public void setFaturaCliente(double faturaCliente) {
        this.faturaCliente = faturaCliente;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    public List<HistoricoRecarga> getHistoricoRecarga() {
        return historicoRecarga;
    }

    public void setHistoricoRecarga(List<HistoricoRecarga> historicoRecarga) {
        this.historicoRecarga = historicoRecarga;
    }
}
