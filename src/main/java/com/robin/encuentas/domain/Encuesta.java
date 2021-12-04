package com.robin.encuentas.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Encuesta {

    private Long id;
    private String nombre;
    private Integer nroPreguntas;
    private List<Pregunta> preguntas;
}
