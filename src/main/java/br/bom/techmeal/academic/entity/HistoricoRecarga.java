package br.bom.techmeal.academic.entity;

import br.bom.techmeal.academic.dto.FornecedorDTO;
import br.bom.techmeal.academic.dto.HistoricoRecargaDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "historico_recarga")
public class HistoricoRecarga implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int idHistoricoRecarga;
    @Temporal(value = TemporalType.DATE)
    private Date dataRecargaHistoricoRecarga;

    @Column(nullable = false)
    private double valorRecargaHistoricoRecarga;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference // Evita a serialização repetitiva
    private Cliente cliente;

    public HistoricoRecarga(HistoricoRecargaDTO historicoRecarga){
        BeanUtils.copyProperties(historicoRecarga, this);
    }

    public HistoricoRecarga(){

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorRecargaHistoricoRecarga() {
        return valorRecargaHistoricoRecarga;
    }

    public void setValorRecargaHistoricoRecarga(double valorRecargaHistoricoRecarga) {
        this.valorRecargaHistoricoRecarga = valorRecargaHistoricoRecarga;
    }

    public Date getDataRecargaHistoricoRecarga() {
        return dataRecargaHistoricoRecarga;
    }

    public void setDataRecargaHistoricoRecarga(Date dataRecargaHistoricoRecarga) {
        this.dataRecargaHistoricoRecarga = dataRecargaHistoricoRecarga;
    }

    public int getIdHistoricoRecarga() {
        return idHistoricoRecarga;
    }

    public void setIdHistoricoRecarga(int idHistoricoRecarga) {
        this.idHistoricoRecarga = idHistoricoRecarga;
    }
}
