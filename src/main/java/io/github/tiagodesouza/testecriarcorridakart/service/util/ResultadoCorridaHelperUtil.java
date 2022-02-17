package io.github.tiagodesouza.testecriarcorridakart.service.util;

import io.github.tiagodesouza.testecriarcorridakart.model.DadosCorrida;
import io.github.tiagodesouza.testecriarcorridakart.model.ResultadoCorrida;
import io.github.tiagodesouza.testecriarcorridakart.repository.DadosCorridaRepository;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class ResultadoCorridaHelperUtil {

    public static List<ResultadoCorrida> resultado(DadosCorridaRepository repository) {

        List<DadosCorrida> dadosCorridas = repository.findAll();

        Map<Integer, ResultadoCorrida> resultadoCorridaMap = dadosCorridas.stream()
                .map(ResultadoCorrida::new)
                .collect(Collectors.toMap(ResultadoCorrida::getNumeroPiloto, resultadoCorrida -> resultadoCorrida, (t, t2) -> {
                    String time = plus(t.getTempoTotalProva(), t2.getTempoTotalProva());
                    t.setTempoTotalProva(time);
                    t.setQteVoltasCompletadas(t2.getQteVoltasCompletadas());
                    return t;
                }));

        List<ResultadoCorrida> resultadoCorridas = resultadoCorridaMap.values()
                .stream()
                .sorted(comparing(ResultadoCorrida::getTempoTotalProva))
                .sorted(comparing(ResultadoCorrida::getQteVoltasCompletadas).reversed())
                .collect(Collectors.toList());

        retornarPosicaoFinalDaCorrida(resultadoCorridas);

        return resultadoCorridas;
    }

    private static String plus(String tempo, String tempo1) {
        LocalTime p1 = LocalTime.parse(tempo);
        LocalTime p2 = LocalTime.parse(tempo1);
        return p1.plus(toDuration(p2)).toString();
    }

    private static Duration toDuration(LocalTime p2) {
        return Duration.ofNanos(p2.toNanoOfDay());
    }

    private static void retornarPosicaoFinalDaCorrida(List<ResultadoCorrida> resultadoCorridas) {
        int posicao = 1;

        for (ResultadoCorrida resultadoCorrida : resultadoCorridas) {
            resultadoCorrida.setPosicao(posicao);
            posicao++;
        }
    }
}
