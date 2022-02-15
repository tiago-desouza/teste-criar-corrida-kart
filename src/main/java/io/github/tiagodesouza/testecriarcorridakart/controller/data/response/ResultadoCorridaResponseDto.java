package io.github.tiagodesouza.testecriarcorridakart.controller.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.time.Duration;

public class ResultadoCorridaResponseDto {

    @JsonProperty(value = "position", index = 1)
    private Integer posicao;
    private Integer numeroPiloto;
    private String nomePiloto;
    private Integer qteVoltasCompletadas;
    private String tempoTotalProva;

    @Deprecated
    public ResultadoCorridaResponseDto() {
    }

    public Integer getPosicao() {
        return posicao;
    }

    public Integer getNumeroPiloto() {
        return numeroPiloto;
    }

    public String getNomePiloto() {
        return nomePiloto;
    }

    public Integer getQteVoltasCompletadas() {
        return qteVoltasCompletadas;
    }

    public String getTempoTotalProva() {
        return tempoTotalProva;
    }
}
