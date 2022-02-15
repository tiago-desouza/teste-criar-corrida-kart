package io.github.tiagodesouza.testecriarcorridakart.service.util;

import io.github.tiagodesouza.testecriarcorridakart.model.DadosCorrida;
import io.github.tiagodesouza.testecriarcorridakart.model.ResultadoCorrida;
import io.github.tiagodesouza.testecriarcorridakart.repository.DadosCorridaRepository;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

public class ResultadoCorridaHelperUtil {

    public static List<ResultadoCorrida> resultado(DadosCorridaRepository repository) {

        List<DadosCorrida> dadosCorridas = repository.findAll();
        List<ResultadoCorrida> resultadoCorridas = new ArrayList<>();

        List<Integer> numeroPilotoList = dadosCorridas.stream()
                .map(DadosCorrida::getNumeroPiloto)
                .distinct()
                .collect(Collectors.toList());


        numeroPilotoList.forEach(numeroPiloto -> {

            ResultadoCorrida resultadoCorrida = new ResultadoCorrida();

            dadosCorridas.stream()
                    .filter(f -> numeroPiloto.equals(f.getNumeroPiloto()))
                    .map(DadosCorrida::getNomePiloto)
                    .distinct()
                    .collect(Collectors.toList())
                    .remove("F.MASS");

            String nomePiloto = dadosCorridas.stream()
                    .filter(f -> numeroPiloto.equals(f.getNumeroPiloto()))
                    .map(DadosCorrida::getNomePiloto)
                    .distinct()
                    .findFirst()
                    .get();

            Duration duration = dadosCorridas.stream()
                    .filter(f -> numeroPiloto.equals(f.getNumeroPiloto()))
                    .map(DadosCorrida::getTempoDaVolta)
                    .map(ResultadoCorridaHelperUtil::converteStringEmDuration)
                    .reduce(Duration.ZERO, Duration::plus);

            int volta = dadosCorridas.stream()
                    .filter(f -> numeroPiloto.equals(f.getNumeroPiloto()))
                    .mapToInt(DadosCorrida::getVolta)
                    .max().orElseThrow(NoSuchElementException::new);

            resultadoCorrida.setNumeroPiloto(numeroPiloto);
            resultadoCorrida.setNomePiloto(nomePiloto);
            resultadoCorrida.setQteVoltasCompletadas(volta);
            resultadoCorrida.setTempoTotalProva(converteDurationEmString(duration));

            resultadoCorridas.add(resultadoCorrida);
        });

        retornarPosicaoFinalDaCorrida(resultadoCorridas);

        return resultadoCorridas;
    }

    private static void retornarPosicaoFinalDaCorrida(List<ResultadoCorrida> resultadoCorridas) {
        int posicao = 1;

        resultadoCorridas.sort(comparing(ResultadoCorrida::getTempoTotalProva));

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
