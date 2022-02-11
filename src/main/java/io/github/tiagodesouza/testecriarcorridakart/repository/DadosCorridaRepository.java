package io.github.tiagodesouza.testecriarcorridakart.repository;

import io.github.tiagodesouza.testecriarcorridakart.model.DadosCorrida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DadosCorridaRepository extends JpaRepository<DadosCorrida, Long> {
    List<DadosCorrida> findBynumeroPiloto(Integer numero);
}
