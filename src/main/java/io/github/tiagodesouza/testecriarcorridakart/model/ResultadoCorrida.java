package io.github.tiagodesouza.testecriarcorridakart.model;

import java.util.Objects;

public class ResultadoCorrida {
    /*
    Posição Chegada, Código Piloto, Nome Piloto, Qtde Voltas Completadas e Tempo Total de Prova
     */
    private Integer posicao;
    private Integer numeroPiloto;
    private String nomePiloto;
    private Integer qteVoltasCompletadas;
    private String tempoTotalProva;

    public Integer getPosicao() {
        return posicao;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }

    public Integer getNumeroPiloto() {
        return numeroPiloto;
    }

    public void setNumeroPiloto(Integer numeroPiloto) {
        this.numeroPiloto = numeroPiloto;
    }

    public String getNomePiloto() {
        return nomePiloto;
    }

    public void setNomePiloto(String nomePiloto) {
        this.nomePiloto = nomePiloto;
    }

    public Integer getQteVoltasCompletadas() {
        return qteVoltasCompletadas;
    }

    public void setQteVoltasCompletadas(Integer qteVoltasCompletadas) {
        this.qteVoltasCompletadas = qteVoltasCompletadas;
    }

    public String getTempoTotalProva() {
        return tempoTotalProva;
    }

    public void setTempoTotalProva(String tempoTotalProva) {
        this.tempoTotalProva = tempoTotalProva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultadoCorrida that = (ResultadoCorrida) o;
        return Objects.equals(numeroPiloto, that.numeroPiloto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroPiloto);
    }

    @Override
    public String toString() {
        return "ResultadoCorrida{" +
                "posicao=" + posicao +
                ", numeroPiloto=" + numeroPiloto +
                ", nomePiloto='" + nomePiloto + '\'' +
                ", qteVoltasCompletadas='" + qteVoltasCompletadas + '\'' +
                ", tempoTotalProva='" + tempoTotalProva + '\'' +
                '}';
    }
}
