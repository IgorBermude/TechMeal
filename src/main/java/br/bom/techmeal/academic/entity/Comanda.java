package br.bom.techmeal.academic.entity;

import br.bom.techmeal.academic.dto.ComandaDTO;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comanda")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCompraComanda")
public class Comanda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCompraComanda;

    @Column(name = "valorTotal")
    private double valorTotalComanda;

    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Sao_Paulo")
    private Date horaEntradaComanda;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "hora_saida_comanda", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Sao_Paulo")
    private Date horaSaidaComanda;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("comanda-comandaProduto")
    private List<ComandaProduto> comandaProdutos; // REMOVA @JsonManagedReference

    public Comanda(ComandaDTO comanda){
        BeanUtils.copyProperties(comanda, this);
    }

    public Comanda(){}

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

    public List<ComandaProduto> getComandaProdutos() {
        return comandaProdutos;
    }

    public void setComandaProdutos(List<ComandaProduto> comandaProdutos) {
        this.comandaProdutos = comandaProdutos;
    }
}

