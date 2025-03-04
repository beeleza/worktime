package com.higorbeleza.worktime.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class RegistroDePonto {
    @Id
    @GeneratedValue
    private Long id;
    private String funcionario;
    private LocalTime entrada;
    private LocalTime saidaAlmoco;
    private LocalTime retornoAlmoco;
    private LocalTime saida;

    private LocalDate data;

    public RegistroDePonto() {}

    public RegistroDePonto(String funcionario, LocalTime entrada, LocalTime saidaAlmoco, LocalTime retornoAlmoco, LocalTime saida, LocalDate data) {
        this.funcionario = funcionario;
        this.entrada = entrada;
        this.saidaAlmoco = saidaAlmoco;
        this.retornoAlmoco = retornoAlmoco;
        this.saida = saida;
        this.data = data;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public LocalTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalTime entrada) {
        this.entrada = entrada;
    }

    public LocalTime getSaidaAlmoco() {
        return saidaAlmoco;
    }

    public void setSaidaAlmoco(LocalTime saidaAlmoco) {
        this.saidaAlmoco = saidaAlmoco;
    }

    public LocalTime getRetornoAlmoco() {
        return retornoAlmoco;
    }

    public void setRetornoAlmoco(LocalTime retornoAlmoco) {
        this.retornoAlmoco = retornoAlmoco;
    }

    public LocalTime getSaida() {
        return saida;
    }

    public void setSaida(LocalTime saida) {
        this.saida = saida;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public long calcularHorasTrabalhadas() {
        if (entrada != null && saida != null && saidaAlmoco != null && retornoAlmoco != null) {
            // Calcula a diferença entre entrada e saída, subtraindo a pausa para o almoço
            long horasTrabalhadas = java.time.Duration.between(entrada, saida).toHours();
            long pausaAlmoco = java.time.Duration.between(saidaAlmoco, retornoAlmoco).toHours();
            return horasTrabalhadas - pausaAlmoco;
        }
        return 0;
    }
}
