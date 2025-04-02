package br.bom.techmeal.academic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AtualizarHoraSaidaDTO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "America/Sao_Paulo")
    private Date horaSaidaComanda;

    public Date getHoraSaidaComanda() {
        return horaSaidaComanda;
    }

    public void setHoraSaidaComanda(Date horaSaidaComanda) {
        this.horaSaidaComanda = horaSaidaComanda;
    }
}
