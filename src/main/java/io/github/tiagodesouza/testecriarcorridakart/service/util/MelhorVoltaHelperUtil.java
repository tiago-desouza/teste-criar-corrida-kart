package io.github.tiagodesouza.testecriarcorridakart.service.util;

import io.github.tiagodesouza.testecriarcorridakart.model.DadosCorrida;
import io.github.tiagodesouza.testecriarcorridakart.model.MelhorVolta;
import io.github.tiagodesouza.testecriarcorridakart.repository.DadosCorridaRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MelhorVoltaHelperUtil {

    public static List<MelhorVolta> melhorVolta(DadosCorridaRepository repository) {
        List<DadosCorrida> dadosCorridas = repository.findAll();

        Map<Integer, MelhorVolta> melhorVoltaMap = dadosCorridas.stream()
                .map(MelhorVolta::new)
                .collect(Collectors.toMap(
                        MelhorVolta::getNumeroPiloto,
                        melhorVolta -> melhorVolta,
                        (t, t2) -> {
                            LocalTime p = LocalTime.parse(t.getMelhorTempo());
                            LocalTime p2 = LocalTime.parse(t2.getMelhorTempo());
                            if(p.toNanoOfDay() < p2.toNanoOfDay()) {
                                return t;
                            }
                            return t2;
                        }
                ));
        return new ArrayList<>(melhorVoltaMap.values());
    }
}
