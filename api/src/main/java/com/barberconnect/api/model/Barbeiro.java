package com.barberconnect.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "barbeiros")
public class Barbeiro extends Usuario {

    private String especialidade;
    private Double percentualComissao;

   
    protected Barbeiro() {
        // Construtor vazio exigido pelo JPA
    }

    public Barbeiro(String nome, String email, String telefone, String especialidade, Double percentualComissao) {
        super(nome, email, telefone); // Repassa para a classe mãe Usuario
        this.especialidade = especialidade;
        this.percentualComissao = percentualComissao;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Double getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(Double percentualComissao) {
        this.percentualComissao = percentualComissao;
    }
}