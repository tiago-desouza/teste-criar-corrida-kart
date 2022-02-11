package io.github.tiagodesouza.testecriarcorridakart.service.util;

import io.github.tiagodesouza.testecriarcorridakart.model.DadosCorrida;
import io.github.tiagodesouza.testecriarcorridakart.model.ResultadoCorrida;
import io.github.tiagodesouza.testecriarcorridakart.repository.DadosCorridaRepository;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

public class ResultadoCorridaHelper {

    public static List<ResultadoCorrida> resultado(DadosCorridaRepository repository) {

        List<DadosCorrida> dadosCorridas = repository.findAll();
        List<ResultadoCorrida> resultadoCorridas = new ArrayList<>();

        List<Integer> numeroPilotoList = dadosCorridas.stream()
                .map(DadosCorrida::getNumeroPiloto)
                .distinct()
                .collect(Collectors.toList());


        numeroPilotoList.forEach(i -> {

            ResultadoCorrida resultadoCorrida = new ResultadoCorrida();

            dadosCorridas.stream()
                    .filter(f -> i.equals(f.getNumeroPiloto()))
                    .map(DadosCorrida::getNomePiloto)
                    .distinct()
                    .collect(Collectors.toList())
                    .remove("F.MASS");

            String nomePiloto = dadosCorridas.stream()
                    .filter(f -> i.equals(f.getNumeroPiloto()))
                    .map(DadosCorrida::getNomePiloto)
                    .distinct()
                    .findFirst()
                    .get();

            Duration duration = dadosCorridas.stream()
                    .filter(f -> i.equals(f.getNumeroPiloto()))
                    .map(DadosCorrida::getTempoDaVolta)
                    .map(ResultadoCorridaHelper::converteStringEmDuration)
                    .reduce(Duration.ZERO, Duration::plus);

            int volta = dadosCorridas.stream()
                    .filter(f -> i.equals(f.getNumeroPiloto()))
                    .mapToInt(DadosCorrida::getVolta)
                    .max().orElseThrow(NoSuchElementException::new);

            resultadoCorrida.setNumeroPiloto(i);
            resultadoCorrida.setNomePiloto(nomePiloto);
            resultadoCorrida.setQteVoltasCompletadas(volta);
            resultadoCorrida.setTempoTotalProva(duration);

            resultadoCorridas.add(resultadoCorrida);
        });

        resultadoCorridas.sort(comparing(ResultadoCorrida::getTempoTotalProva));

        int posicao = 1;
        for (ResultadoCorrida resultadoCorrida : resultadoCorridas) {
            resultadoCorrida.setPosicao(posicao);
            posicao++;
        }

        return resultadoCorridas;
    }

    private static Duration converteStringEmDuration(String time) {
        String[] times = time.replace(".", ":").split(":");
        Duration duration = Duration.ofMinutes(Long.parseLong(times[0]));
        duration = duration.plusSeconds(Long.parseLong(times[1]));
        duration = duration.plusMillis(Long.parseLong(times[2]));
        return duration;
    }
}
