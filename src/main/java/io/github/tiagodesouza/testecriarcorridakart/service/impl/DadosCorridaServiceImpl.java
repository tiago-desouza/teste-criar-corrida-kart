package io.github.tiagodesouza.testecriarcorridakart.service.impl;

import io.github.tiagodesouza.testecriarcorridakart.model.DadosCorrida;
import io.github.tiagodesouza.testecriarcorridakart.model.MelhorVolta;
import io.github.tiagodesouza.testecriarcorridakart.model.ResultadoCorrida;
import io.github.tiagodesouza.testecriarcorridakart.model.VelocidadeMedia;
import io.github.tiagodesouza.testecriarcorridakart.repository.DadosCorridaRepository;
import io.github.tiagodesouza.testecriarcorridakart.service.DadosCorridaService;
import io.github.tiagodesouza.testecriarcorridakart.service.util.FileHelperDadosCorridaUtil;
import io.github.tiagodesouza.testecriarcorridakart.service.util.MelhorVoltaHelperUtil;
import io.github.tiagodesouza.testecriarcorridakart.service.util.ResultadoCorridaHelperUtil;
import io.github.tiagodesouza.testecriarcorridakart.service.util.VelocidadeMediaHelperUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class DadosCorridaServiceImpl implements DadosCorridaService {

    private final DadosCorridaRepository repository;

    public DadosCorridaServiceImpl(DadosCorridaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void uploadDadosCorrida(MultipartFile file) {
        repository.saveAll(FileHelperDadosCorridaUtil.txtParaData(file));
    }

    @Override
    public List<DadosCorrida> getDadosCorrida() {
        return repository.findAll();
    }

    @Override
    public List<DadosCorrida> getDadosPorPiloto(Integer numeroPiloto) {
        return repository.findBynumeroPiloto(numeroPiloto);
    }

    @Override
    public List<ResultadoCorrida> getResultadoCorrida() {
        return ResultadoCorridaHelperUtil.resultado(repository);
    }

    @Override
    public List<MelhorVolta> getMelhorVoltaPorPiloto() {
        return MelhorVoltaHelperUtil.melhorVoltaPorPiloto(repository);
    }

    @Override
    public MelhorVolta getMelhorVolta() {
        return MelhorVoltaHelperUtil.melhorVolta(repository);
    }

    @Override
    public List<VelocidadeMedia> getVelocidadeMediaPiloto() {
        return VelocidadeMediaHelperUtil.velocidadeMediaPorPiloto(repository);
    }

}
