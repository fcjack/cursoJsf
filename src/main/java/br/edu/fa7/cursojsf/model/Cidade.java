package br.edu.fa7.cursojsf.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Cidade extends AbstractEntity {

    private String nome;
    private Long populacao;
    private BigDecimal pib;

    @Temporal(TemporalType.DATE)
    private Date dataContituicao;

    @ManyToOne
    private Estado estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataContituicao() {
        return dataContituicao;
    }

    public void setDataContituicao(Date dataContituicao) {
        this.dataContituicao = dataContituicao;
    }

    public Long getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Long populacao) {
        this.populacao = populacao;
    }

    public BigDecimal getPib() {
        return pib;
    }

    public void setPib(BigDecimal pib) {
        this.pib = pib;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
