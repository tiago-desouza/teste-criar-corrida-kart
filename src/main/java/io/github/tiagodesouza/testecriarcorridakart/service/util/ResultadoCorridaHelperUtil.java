package io.github.tiagodesouza.testecriarcorridakart.service.util;

import io.github.tiagodesouza.testecriarcorridakart.model.DadosCorrida;
import io.github.tiagodesouza.testecriarcorridakart.model.ResultadoCorrida;
import io.github.tiagodesouza.testecriarcorridakart.repository.DadosCorridaRepository;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class ResultadoCorridaHelperUtil {

    public static List<ResultadoCorrida> resultado(DadosCorridaRepository repository) {

        List<DadosCorrida> dadosCorridas = repository.findAll();

        Map<Integer, ResultadoCorrida> resultadoCorridaMap = dadosCorridas.stream()
                .map(dadosCorrida -> {
                    ResultadoCorrida resultadoCorrida = new ResultadoCorrida();
                    resultadoCorrida.setNumeroPiloto(dadosCorrida.getNumeroPiloto());
                    resultadoCorrida.setNomePiloto(dadosCorrida.getNomePiloto());
                    resultadoCorrida.setQteVoltasCompletadas(dadosCorrida.getVolta());
                    resultadoCorrida.setTempoTotalProva(dadosCorrida.getTempoDaVolta());
                    return resultadoCorrida;
                }).collect(Collectors.toMap(ResultadoCorrida::getNumeroPiloto, resultadoCorrida -> resultadoCorrida, (t, t2) -> {
                    Duration time = converteStringEmDuration(t.getTempoTotalProva()).plus(converteStringEmDuration(t2.getTempoTotalProva()));
                    t.setTempoTotalProva(converteDurationEmString(time));
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

    private static void retornarPosicaoFinalDaCorrida(List<ResultadoCorrida> resultadoCorridas) {
        int posicao = 1;

        for (ResultadoCorrida resultadoCorrida : resultadoCorridas) {
            resultadoCorrida.setPosicao(posicao);
            posicao++;
        }
    }

    private static Duration converteStringEmDuration(String time) {
        String[] times = time.replace(".", ":").split(":");

        return Duration.ofMinutes(Long.parseLong(times[0]))
                .plusSeconds(Long.parseLong(times[1]))
                .plusMillis(Long.parseLong(times[2]));
    }

    private static String converteDurationEmString(Duration duration) {

        String time = duration.toString();

        return time.replace("PT", "")
                .replace("M", ":")
                .replace("S", "");
    }
}
