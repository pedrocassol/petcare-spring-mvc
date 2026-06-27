package br.com.petcare.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class Consulta {

    private Integer id;

    @NotNull(message = "Selecione um pet.")
    @Min(value = 1, message = "Selecione um pet válido.")
    private Integer idPet;

    private String nomePet;
    private String nomeProprietario;

    @NotBlank(message = "Informe a data e a hora.")
    @Size(max = 30, message = "A data e a hora são inválidas.")
    private String dataHora;

    @NotBlank(message = "Informe o veterinário.")
    @Size(max = 100, message = "O nome do veterinário deve ter no máximo 100 caracteres.")
    private String veterinario;

    @NotBlank(message = "Informe a descrição da consulta.")
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres.")
    private String descricao;

    @NotNull(message = "Informe o valor estimado.")
    @PositiveOrZero(message = "O valor estimado não pode ser negativo.")
    private BigDecimal valorEstimado;

    @NotBlank(message = "Informe o status.")
    @Size(max = 20, message = "O status deve ter no máximo 20 caracteres.")
    private String status;

    @Size(max = 500, message = "As observações devem ter no máximo 500 caracteres.")
    private String observacoes;

    public Consulta() {
    }

    public Consulta(Integer idPet, String dataHora, String veterinario, String descricao,
                    BigDecimal valorEstimado, String status, String observacoes) {
        this.idPet = idPet;
        this.dataHora = dataHora;
        this.veterinario = veterinario;
        this.descricao = descricao;
        this.valorEstimado = valorEstimado;
        this.status = status;
        this.observacoes = observacoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPet() {
        return idPet;
    }

    public void setIdPet(Integer idPet) {
        this.idPet = idPet;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(String veterinario) {
        this.veterinario = veterinario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(BigDecimal valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
