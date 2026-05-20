package com.barberconnect.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoRequestDTO {

    private LocalDateTime dataHora;
    private Long clienteId;
    private Long barbeiroId;
    private List<Long> servicoIds;

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getBarbeiroId() {
        return barbeiroId;
    }

    public void setBarbeiroId(Long barbeiroId) {
        this.barbeiroId = barbeiroId;
    }

    public List<Long> getServicoIds() {
        return servicoIds;
    }

    public void setServicoIds(List<Long> servicoIds) {
        this.servicoIds = servicoIds;
    }
}
