package br.bom.techmeal.academic.dto;

import br.bom.techmeal.academic.entity.Cliente;
import br.bom.techmeal.academic.entity.Fornecedor;
import br.bom.techmeal.academic.entity.HistoricoRecarga;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Date;

public class HistoricoRecargaDTO {
    private int idHistoricoRecarga;
    private Date dataRecargaHistoricoRecarga;
    private double valorRecargaHistoricoRecarga;
    private Cliente cliente;

    public HistoricoRecargaDTO(HistoricoRecarga historicoRecarga){
        BeanUtils.copyProperties(historicoRecarga, this);
    }

    public HistoricoRecargaDTO(){

    }

    public int getIdHistoricoRecarga() {
        return idHistoricoRecarga;
    }

    public void setIdHistoricoRecarga(int idHistoricoRecarga) {
        this.idHistoricoRecarga = idHistoricoRecarga;
    }

    public Date getDataRecargaHistoricoRecarga() {
        return dataRecargaHistoricoRecarga;
    }

    public void setDataRecargaHistoricoRecarga(Date dataRecargaHistoricoRecarga) {
        this.dataRecargaHistoricoRecarga = dataRecargaHistoricoRecarga;
    }

    public double getValorRecargaHistoricoRecarga() {
        return valorRecargaHistoricoRecarga;
    }

    public void setValorRecargaHistoricoRecarga(double valorRecargaHistoricoRecarga) {
        this.valorRecargaHistoricoRecarga = valorRecargaHistoricoRecarga;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
