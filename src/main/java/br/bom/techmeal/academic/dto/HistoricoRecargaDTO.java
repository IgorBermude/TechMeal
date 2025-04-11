package br.bom.techmeal.academic.dto;

public class HistoricoRecargaDTO {
    private double valorRecargaHistoricoRecarga;
    private int idCliente;

    // Getters e setters
    public double getValorRecargaHistoricoRecarga() {
        return valorRecargaHistoricoRecarga;
    }

    public void setValorRecargaHistoricoRecarga(double valor) {
        this.valorRecargaHistoricoRecarga = valor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}

