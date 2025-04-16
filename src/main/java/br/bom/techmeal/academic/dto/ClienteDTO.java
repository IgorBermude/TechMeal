package br.bom.techmeal.academic.dto;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.Comanda;
import br.bom.techmeal.academic.entity.HistoricoRecarga;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.BeanUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ClienteDTO {
    private int idCliente;
    private String nomeCliente;
    private double saldoCliente;
    private double limiteCliente;
    private Date dtNascCliente;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date ultimaCompraCliente;
    private double faturaCliente;
    private String idCartaoCliente;


    private List<Comanda> comandas;


    private List<HistoricoRecarga> historicoRecarga;

    public ClienteDTO(Cliente cliente){
        BeanUtils.copyProperties(cliente, this);
        // Sobrescreve a data para evitar ajuste duplicado
        this.dtNascCliente = cliente.getDtNascCliente();
        this.ultimaCompraCliente = cliente.getUltimaCompraCliente();
        this.historicoRecarga = cliente.getHistoricoRecarga(); 
    }

    public ClienteDTO(){ }

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

    public String getIdCartaoCliente() {
        return idCartaoCliente;
    }

    public void setIdCartaoCliente(String idCartaoCliente) {
        this.idCartaoCliente = idCartaoCliente;
    }

    public void setLimiteCliente(double limiteCliente) {
        this.limiteCliente = limiteCliente;
    }

    public Date getDtNascCliente() {
        return dtNascCliente;
    }

    public void setDtNascCliente(Date dtNascCliente) {
        if (dtNascCliente != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dtNascCliente);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            // Adiciona 1 dia para compensar (necessário apenas para entrada, se assim você configurou)
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            this.dtNascCliente = new java.sql.Date(calendar.getTimeInMillis());
        } else {
            this.dtNascCliente = null;
        }
    }

    public Date getUltimaCompraCliente() {
        return ultimaCompraCliente;
    }

    public void setUltimaCompraCliente(Date ultimaCompraCliente) {
        if (ultimaCompraCliente != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ultimaCompraCliente);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            // Adiciona 1 dia para compensar (necessário apenas para entrada, se assim você configurou)
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            this.ultimaCompraCliente = new java.sql.Date(calendar.getTimeInMillis());
        } else {
            this.ultimaCompraCliente = null;
        }
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
