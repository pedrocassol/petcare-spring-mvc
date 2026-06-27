package br.com.petcare.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class Pet {

    private Integer id;

    @NotBlank(message = "Informe o nome do pet.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    private String nome;

    @NotBlank(message = "Informe a espécie.")
    @Size(max = 50, message = "A espécie deve ter no máximo 50 caracteres.")
    private String especie;

    @Size(max = 100, message = "A raça deve ter no máximo 100 caracteres.")
    private String raca;

    @PositiveOrZero(message = "A idade não pode ser negativa.")
    private Integer idade;

    @NotBlank(message = "Informe o sexo.")
    @Size(max = 20, message = "O sexo deve ter no máximo 20 caracteres.")
    private String sexo;

    @Size(max = 500, message = "As observações devem ter no máximo 500 caracteres.")
    private String observacoes;

    @NotNull(message = "Selecione um proprietário.")
    @Min(value = 1, message = "Selecione um proprietário válido.")
    private Integer idProprietario;

    private String nomeProprietario;

    public Pet() {
    }

    public Pet(String nome, String especie, String raca, Integer idade, String sexo,
               String observacoes, Integer idProprietario) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.sexo = sexo;
        this.observacoes = observacoes;
        this.idProprietario = idProprietario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(Integer idProprietario) {
        this.idProprietario = idProprietario;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }
}
