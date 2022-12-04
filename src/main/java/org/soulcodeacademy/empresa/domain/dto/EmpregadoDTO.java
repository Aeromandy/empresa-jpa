package org.soulcodeacademy.empresa.domain.dto;


import org.soulcodeacademy.empresa.domain.Endereco;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EmpregadoDTO {

    @NotNull(message = "nome obrigatório")
    private String nome;

    @NotBlank(message = "email obrigatório")
    @Email(message = " email inválido")
    private String email;

    @NotNull(message = "salário obrigatório")
    @Min(value = 3000, message = "campo salário inválido")
    private Double salario;

    @NotNull
    private Integer idEndereco;

    private Integer idProjeto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Integer getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Integer idProjeto) {
        this.idProjeto = idProjeto;
    }


}
