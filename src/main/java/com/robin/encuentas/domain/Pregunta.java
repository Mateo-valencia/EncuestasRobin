package com.robin.encuentas.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pregunta {

    private Long id;
    private Long cdEncuesta;
    private String descripcion;
    private String tipoRespuesta;
    private List<Respuesta> respuestas;
}
