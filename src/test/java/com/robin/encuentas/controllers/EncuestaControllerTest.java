package com.robin.encuentas.controllers;

import com.robin.encuentas.domain.Encuesta;
import com.robin.encuentas.domain.Pregunta;
import com.robin.encuentas.domain.Respuesta;
import com.robin.encuentas.dto.EncuestaDto;
import com.robin.encuentas.exceptions.MyException;
import com.robin.encuentas.services.EncuestaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class EncuestaControllerTest {

    @Mock
    private Logger logger;

    @Mock
    private EncuestaService encuestaService;

    @Mock
    private ModelMapper modelMapper;

    private EncuestaController encuestaController;

    private Encuesta encuesta;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        encuestaController = new EncuestaController(encuestaService,modelMapper);

        Pregunta pregunta = Pregunta.builder()
                .cdEncuesta(1L)
                .descripcion("TEST")
                .tipoRespuesta("ABIERTA")
                .respuestas(Collections.emptyList())
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
    void guardarTest(){
        when(modelMapper.map(any(),any()))
                .thenReturn(encuesta);

        Assertions.assertEquals("Encuesta Guardada con Exito.",encuestaController.guardar(new EncuestaDto()));
    }

    @Test
    void guardarExceptionTest(){
        encuesta = encuesta.toBuilder().nroPreguntas(0).build();

        when(modelMapper.map(any(),any()))
                .thenReturn(encuesta);

        Assertions.assertTrue(encuestaController.guardar(new EncuestaDto()).contains("Ocurrio un Error al Guardar La encuesta:"));
    }

    @Test
    void guardarException2Test(){
        List<Pregunta>  preguntas = encuesta.getPreguntas().stream()
                .map(pregunta -> pregunta.toBuilder().tipoRespuesta("MULTIPLE").build())
                .collect(Collectors.toList());

        encuesta = encuesta.toBuilder()
                .nroPreguntas(1)
                .preguntas(preguntas)
                .build();

        when(modelMapper.map(any(),any()))
                .thenReturn(encuesta);

        Assertions.assertTrue(encuestaController.guardar(new EncuestaDto()).contains("Ocurrio un Error al Guardar La encuesta:"));
    }

    @Test
    void guardarException3Test(){
        List<Pregunta>  preguntas = encuesta.getPreguntas().stream()
                .map(pregunta ->
                        pregunta.toBuilder()
                        .respuestas(Collections.singletonList(new Respuesta(1L,1L,"prueba")))
                        .build()
                )
                .collect(Collectors.toList());

        encuesta = encuesta.toBuilder()
                .nroPreguntas(1)
                .preguntas(preguntas)
                .build();

        when(modelMapper.map(any(),any()))
                .thenReturn(encuesta);

        Assertions.assertTrue(encuestaController.guardar(new EncuestaDto()).contains("Ocurrio un Error al Guardar La encuesta:"));
    }

    @Test
    void guardarException4Test(){
        List<Pregunta>  preguntas = encuesta.getPreguntas().stream()
                .map(pregunta ->
                        pregunta.toBuilder()
                                .tipoRespuesta("Prueba")
                                .build()
                )
                .collect(Collectors.toList());

        encuesta = encuesta.toBuilder()
                .nroPreguntas(1)
                .preguntas(preguntas)
                .build();

        when(modelMapper.map(any(),any()))
                .thenReturn(encuesta);

        Assertions.assertTrue(encuestaController.guardar(new EncuestaDto()).contains("Ocurrio un Error al Guardar La encuesta:"));
    }

    @Test
    void consultarTest() throws MyException {
        when(encuestaService.consultar(anyLong()))
                .thenReturn(encuesta);

        when(modelMapper.map(any(),any()))
                .thenReturn(new EncuestaDto());

        Assertions.assertNotNull(encuestaController.consultar(1L));
    }

    @Test
    void consultarExceptionTest() {
        when(encuestaService.consultar(anyLong()))
                .thenReturn(null);


         Assertions.assertThrows(MyException.class, () ->
                encuestaController.consultar(1L), "No fue posible encontrar La Encuesta.");

    }
}