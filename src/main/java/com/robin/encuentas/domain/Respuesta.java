package com.robin.encuentas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respuesta {

    private Long id;
    private Long cdPregunta;
    private String descripcion;
}
