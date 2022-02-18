package io.github.tiagodesouza.testecriarcorridakart.controller;

import io.github.tiagodesouza.testecriarcorridakart.controller.data.response.MelhorVoltaResponseDto;
import io.github.tiagodesouza.testecriarcorridakart.model.DadosCorrida;
import io.github.tiagodesouza.testecriarcorridakart.model.MelhorVolta;
import io.github.tiagodesouza.testecriarcorridakart.model.ResultadoCorrida;
import io.github.tiagodesouza.testecriarcorridakart.model.VelocidadeMedia;
import io.github.tiagodesouza.testecriarcorridakart.service.DadosCorridaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dadoscorrida")
public class DadosCorridaController {

    private final DadosCorridaService service;

    public DadosCorridaController(DadosCorridaService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadDadosCorrida(MultipartFile file) {
        service.uploadDadosCorrida(file);
    }

    @GetMapping
    public List<DadosCorrida> retornaDadosCorrida(){
        return service.getDadosCorrida();
    }

    @GetMapping("/{numeroPiloto}")
    public List<DadosCorrida> retornaDadosPorPiloto(@PathVariable("numeroPiloto") Integer numeroPiloto) {
        return service.getDadosPorPiloto(numeroPiloto);
    }

    @GetMapping("/resultado")
    public List<ResultadoCorrida> getResultadoCorrida(){
        return service.getResultadoCorrida();
    }

    @GetMapping("/melhorVoltaPorPiloto")
    public List<MelhorVolta> getMelhorVoltaPorPiloto() {
        return service.getMelhorVoltaPorPiloto();
    }

    @GetMapping("/melhorVolta")
    public MelhorVolta getMelhorVolta(){
        return service.getMelhorVolta();
    }

    @GetMapping("/velocidadeMediaPorPiloto")
    public List<VelocidadeMedia> getVelocidadeMediaPiloto(){
        return service.getVelocidadeMediaPiloto();
    }
}
