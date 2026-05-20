package com.barberconnect.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {

    private Integer historicoFidelidade = 0;

    protected Cliente() {
        // Constructor required by JPA.
    }

    public Integer getHistoricoFidelidade() {
        return historicoFidelidade;
    }

    public void setHistoricoFidelidade(Integer historicoFidelidade) {
        this.historicoFidelidade = historicoFidelidade;
    }
    
    // Método simulando regra de negócio
    public void adicionarPontoFidelidade() {
        this.historicoFidelidade++;
    }
}
