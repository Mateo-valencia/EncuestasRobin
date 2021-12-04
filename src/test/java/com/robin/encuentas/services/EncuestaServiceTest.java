package com.robin.encuentas.services;

import com.robin.encuentas.Data.EncuestaData;
import com.robin.encuentas.Data.PreguntaData;
import com.robin.encuentas.Data.RespuestaData;
import com.robin.encuentas.domain.Encuesta;
import com.robin.encuentas.domain.Pregunta;
import com.robin.encuentas.domain.Respuesta;
import com.robin.encuentas.repositories.EncuestaRepository;
import com.robin.encuentas.repositories.PreguntaRepository;
import com.robin.encuentas.repositories.RespuestaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class EncuestaServiceTest {

    @Mock
    private EncuestaRepository encuestaRepository;

    @Mock
    private PreguntaRepository preguntaRepository;

    @Mock
    private RespuestaRepository respuestaRepository;

    @Mock
    private ModelMapper modelMapper;

    private EncuestaService encuestaService;

    private EncuestaData encuestaData;
    private PreguntaData preguntaData;
    private RespuestaData respuestaData;

    private Encuesta encuesta;
    private Pregunta pregunta;
    private Respuesta respuesta;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        encuestaService = new EncuestaService(encuestaRepository,preguntaRepository,respuestaRepository,modelMapper);

        encuestaData = new EncuestaData();
        encuestaData.setId(1L);
        encuestaData.setNombre("Test");
        encuestaData.setNroPreguntas(1);

        preguntaData = new PreguntaData();
        preguntaData.setId(1L);
        preguntaData.setCdEncuesta(1L);
        preguntaData.setDescripcion("TEST");
        preguntaData.setTipoRespuesta("Multiple");

        respuestaData = new RespuestaData();
        respuestaData.setId(1L);
        respuestaData.setCdPregunta(1L);
        respuestaData.setDescripcion("TEST");

        respuesta = Respuesta.builder()
                .descripcion("TEST")
                .build();

        pregunta = Pregunta.builder()
                .cdEncuesta(1L)
                .descripcion("TEST")
                .tipoRespuesta("MULTIPLE")
                .respuestas(Collections.singletonList(respuesta))
                .id(1L)
                .build();

        encuesta = Encuesta.builder()
                .nombre("TEST")
                .nroPreguntas(1)
                .preguntas(Collections.singletonList(pregunta))
                .id(1L)
                .build();
    }

    @Test
    void guardarTest() {
        when(modelMapper.map(any(), eq(EncuestaData.class)))
                .thenReturn(encuestaData);

        when(encuestaRepository.save(any()))
                .thenReturn(encuestaData);

        when(modelMapper.map(any(), eq(PreguntaData.class)))
                .thenReturn(preguntaData);

        when(preguntaRepository.save(any()))
                .thenReturn(preguntaData);

        when(modelMapper.map(any(), eq(RespuestaData.class)))
                .thenReturn(respuestaData);

        when(respuestaRepository.save(any()))
                .thenReturn(respuestaData);

        Assertions.assertEquals(1L,encuestaService.guardar(encuesta));
    }

    @Test
    void consultarTest(){
        when(encuestaRepository.findById(anyLong()))
                .thenReturn(Optional.of(encuestaData));

        when(modelMapper.map(any(), eq(Encuesta.class)))
                .thenReturn(encuesta);

        when(preguntaRepository.findByCdEncuesta(anyLong()))
                .thenReturn(Optional.of(Collections.singletonList(preguntaData)));

        when(modelMapper.map(any(), eq(Pregunta.class)))
                .thenReturn(pregunta);

        when(respuestaRepository.findAllByCdPregunta(anyLong()))
                .thenReturn(Optional.of(Collections.singletonList(respuestaData)));

        when(modelMapper.map(any(), eq(Respuesta.class)))
                .thenReturn(respuesta);

        Assertions.assertNotNull(encuestaService.consultar(1L));
    }
}