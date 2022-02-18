package io.github.tiagodesouza.testecriarcorridakart.service;

import io.github.tiagodesouza.testecriarcorridakart.model.DadosCorrida;
import io.github.tiagodesouza.testecriarcorridakart.model.MelhorVolta;
import io.github.tiagodesouza.testecriarcorridakart.model.ResultadoCorrida;
import io.github.tiagodesouza.testecriarcorridakart.model.VelocidadeMedia;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DadosCorridaService {

    void uploadDadosCorrida(MultipartFile file);

    List<DadosCorrida> getDadosCorrida();

    List<DadosCorrida> getDadosPorPiloto(Integer numeroPiloto);

    List<ResultadoCorrida> getResultadoCorrida();

    List<MelhorVolta> getMelhorVoltaPorPiloto();

    MelhorVolta getMelhorVolta();

    List<VelocidadeMedia> getVelocidadeMediaPiloto();
}
