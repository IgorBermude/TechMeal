package br.bom.techmeal.academic.entity;

import br.bom.techmeal.academic.dto.ClienteDTO;
import br.bom.techmeal.academic.dto.ProdutoDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int idCliente;
    @Column(nullable = false)
    private String nomeCliente;
    @Column ( nullable = false , length = 5)
    private double saldoCliente;
    @Column ( nullable = false , length = 5)
    private double limiteCliente;
    @Temporal(value = TemporalType.DATE)
    private Date dtNascCliente;
    @Temporal(value = TemporalType.DATE)
    private Date ultimaCompraCliente;
    @Column (name = "limite_disponivel")
    private double faturaCliente;
    @Column(unique = true)
    private String idCartaoCliente;

    @OneToMany(mappedBy ="cliente")

    private List<Comanda> comandas;

    @OneToMany(mappedBy = "cliente")

    private List<HistoricoRecarga> historicoRecarga;

    public Cliente(ClienteDTO cliente){
        BeanUtils.copyProperties(cliente, this);
        this.faturaCliente = cliente.getLimiteCliente();
    }

    public Cliente(){

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

    public String getIdCartaoCliente() {
        return idCartaoCliente;
    }

    public void setIdCartaoCliente(String idCartaoCliente) {
        this.idCartaoCliente = idCartaoCliente;
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

    public void recarregarCartão(){

    }
}
