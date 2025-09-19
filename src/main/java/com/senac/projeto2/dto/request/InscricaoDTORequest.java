package com.senac.projeto2.dto.request;

import java.time.LocalDateTime;

public class InscricaoDTORequest {
    private LocalDateTime data;
    private Integer idParticipante;
    private Integer idJogo;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Integer getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Integer idParticipante) {
        this.idParticipante = idParticipante;
    }

    public Integer getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(Integer idJogo) {
        this.idJogo = idJogo;
    }
}
