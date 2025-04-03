package br.bom.techmeal.academic.entity;

import br.bom.techmeal.academic.dto.ComandaDTO;
import br.bom.techmeal.academic.dto.ControleContasDTO;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Sao_Paulo") // <- Ajuste para formatar corretamente
    private Date horaEntradaComanda;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "hora_saida_comanda", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Sao_Paulo") // <- Ajuste para formatar corretame
    private Date horaSaidaComanda;

    @ManyToOne

    private Cliente cliente;

    @ManyToMany
    @JoinTable(
            name = "comanda_produto",
            joinColumns = @JoinColumn(name = "comanda_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )

    private List<Produto> produtoListComanda;

    public Comanda(ComandaDTO comanda){
        BeanUtils.copyProperties(comanda, this);
    }

    public Comanda(){
    }

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

    public List<Produto> getProdutoListComanda() {
        return produtoListComanda;
    }

    public void setProdutoListComanda(List<Produto> produtoListComanda) {
        this.produtoListComanda = produtoListComanda;
    }

    public void addProduto(Produto produto) {
        if (produto != null) {
            if (produtoListComanda == null) {
                produtoListComanda = new ArrayList<>();
            }
            produtoListComanda.add(produto);
        }
    }

}