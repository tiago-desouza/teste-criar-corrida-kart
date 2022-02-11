package io.github.tiagodesouza.testecriarcorridakart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class DadosCorrida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hora;
    private Integer numeroPiloto;
    private String nomePiloto;
    private Integer volta;
    private String tempoDaVolta;
    private Double velocidadeMedia;

    public DadosCorrida() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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

    public Integer getVolta() {
        return volta;
    }

    public void setVolta(Integer volta) {
        this.volta = volta;
    }

    public String getTempoDaVolta() {
        return tempoDaVolta;
    }

    public void setTempoDaVolta(String tempoDaVolta) {
        this.tempoDaVolta = tempoDaVolta;
    }

    public Double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(Double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DadosCorrida that = (DadosCorrida) o;
        return Objects.equals(numeroPiloto, that.numeroPiloto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroPiloto);
    }

    @Override
    public String toString() {
        return "DadosCorrida{" +
                "id=" + id +
                ", hora='" + hora + '\'' +
                ", numeroPiloto=" + numeroPiloto +
                ", nomePiloto='" + nomePiloto + '\'' +
                ", volta=" + volta +
                ", tempoDaVolta='" + tempoDaVolta + '\'' +
                ", velocidadeMedia=" + velocidadeMedia +
                '}';
    }
}
