package com.barberconnect.api.dto;

public class BarbeiroRequestDTO {

    private String nome;
    private String email;
    private String telefone;
    private String especialidade;
    private Double percentualComissao;
    
    public BarbeiroRequestDTO() {
        // Construtor vazio exigido pelo Jackson para a conversão de JSON
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public Double getPercentualComissao() { return percentualComissao; }
    public void setPercentualComissao(Double percentualComissao) { this.percentualComissao = percentualComissao; }
}