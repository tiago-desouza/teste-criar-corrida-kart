package io.github.tiagodesouza.testecriarcorridakart.service.util;

import io.github.tiagodesouza.testecriarcorridakart.model.DadosCorrida;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileHelperDadosCorridaUtil {

    public static List<DadosCorrida> txtParaData(MultipartFile file) {

        try {
            BufferedReader read = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<DadosCorrida> dadosCorridas = new ArrayList<>();

            List<String[]> readList = read.lines()
                    .skip(1)
                    .map(s -> s.split("\\s+"))
                    .collect(Collectors.toList());

            readList.forEach(info -> {
                DadosCorrida dadosCorrida = new DadosCorrida();
                dadosCorrida.setHora(info[0]);
                dadosCorrida.setNumeroPiloto(Integer.parseInt(info[1]));
                dadosCorrida.setNomePiloto(info[3]);
                dadosCorrida.setVolta(Integer.parseInt(info[4]));
                dadosCorrida.setTempoDaVolta("00:0" + info[5]);
                dadosCorrida.setVelocidadeMedia(Double.parseDouble(info[6].replace(",", ".")));
                dadosCorridas.add(dadosCorrida);
            });

            return dadosCorridas;

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
