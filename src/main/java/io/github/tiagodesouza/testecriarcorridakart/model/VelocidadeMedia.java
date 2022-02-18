package io.github.tiagodesouza.testecriarcorridakart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VelocidadeMedia {

    private Integer numeroPiloto;
    private String nomePiloto;
    private Integer voltas;
    private Double velocidadeMedia;

    public VelocidadeMedia(DadosCorrida dadosCorrida) {
        this.numeroPiloto = dadosCorrida.getNumeroPiloto();
        this.nomePiloto = dadosCorrida.getNomePiloto();
        this.voltas = dadosCorrida.getVolta();
        this.velocidadeMedia = dadosCorrida.getVelocidadeMedia();
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

    public Integer getVoltas() {
        return voltas;
    }

    public void setVoltas(Integer voltas) {
        this.voltas = voltas;
    }

    public Double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(Double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }
}
