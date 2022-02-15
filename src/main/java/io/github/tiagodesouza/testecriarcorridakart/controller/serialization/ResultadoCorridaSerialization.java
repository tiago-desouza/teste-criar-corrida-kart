package io.github.tiagodesouza.testecriarcorridakart.controller.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.tiagodesouza.testecriarcorridakart.controller.data.response.ResultadoCorridaResponseDto;
import io.github.tiagodesouza.testecriarcorridakart.model.ResultadoCorrida;
import org.modelmapper.ModelMapper;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class ResultadoCorridaSerialization extends JsonSerializer<ResultadoCorrida> {

    private final ModelMapper modelMapper;

    public ResultadoCorridaSerialization(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void serialize(ResultadoCorrida resultadoCorrida, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ResultadoCorridaResponseDto resultadoCorridaResponseDto = modelMapper.map(resultadoCorrida, ResultadoCorridaResponseDto.class);
        jsonGenerator.writeObject(resultadoCorridaResponseDto);
    }
}
