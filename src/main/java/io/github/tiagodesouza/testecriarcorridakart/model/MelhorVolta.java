package io.github.tiagodesouza.testecriarcorridakart.model;

public class MelhorVolta {

    private Integer numeroPiloto;
    private String nomePiloto;
    private Integer melhorVolta;
    private String melhorTempo;

    public MelhorVolta(DadosCorrida dadosCorrida) {
        this.numeroPiloto = dadosCorrida.getNumeroPiloto();
        this.nomePiloto = dadosCorrida.getNomePiloto();
        this.melhorVolta = dadosCorrida.getVolta();
        this.melhorTempo = dadosCorrida.getTempoDaVolta();
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

    public Integer getMelhorVolta() {
        return melhorVolta;
    }

    public void setMelhorVolta(Integer melhorVolta) {
        this.melhorVolta = melhorVolta;
    }

    public String getMelhorTempo() {
        return melhorTempo;
    }

    public void setMelhorTempo(String melhorTempo) {
        this.melhorTempo = melhorTempo;
    }
}
