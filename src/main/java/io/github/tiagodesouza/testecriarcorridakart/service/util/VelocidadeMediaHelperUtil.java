package io.github.tiagodesouza.testecriarcorridakart.service.util;

import io.github.tiagodesouza.testecriarcorridakart.model.DadosCorrida;
import io.github.tiagodesouza.testecriarcorridakart.model.VelocidadeMedia;
import io.github.tiagodesouza.testecriarcorridakart.repository.DadosCorridaRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VelocidadeMediaHelperUtil {

    public static List<VelocidadeMedia> velocidadeMediaPorPiloto(DadosCorridaRepository repository) {
        List<DadosCorrida> dadosCorridas = repository.findAll();

        Map<Integer, VelocidadeMedia> mediaMap = dadosCorridas.stream()
                .map(VelocidadeMedia::new)
                .collect(Collectors.toMap(
                        VelocidadeMedia::getNumeroPiloto,
                        velocidadeMedia -> velocidadeMedia,
                        (t, t2) -> {
                            t.setVelocidadeMedia(t.getVelocidadeMedia() + t2.getVelocidadeMedia());
                            t.setVoltas(t2.getVoltas());
                            return t;
                        }));

        List<VelocidadeMedia> velocidadeMedia = new ArrayList<>(mediaMap.values());
        velocidadeMedia.forEach(vm -> {
            double valueScale = BigDecimal.valueOf(vm.getVelocidadeMedia() / vm.getVoltas())
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue();
            vm.setVelocidadeMedia(valueScale);
        });

        return velocidadeMedia;
    }

}
