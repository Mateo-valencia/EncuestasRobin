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
public class Pregunta {

    private Long id;
    private Long cdEncuesta;
    private String descripcion;
    private String tipoRespuesta;
    private List<Respuesta> respuestas;
}
