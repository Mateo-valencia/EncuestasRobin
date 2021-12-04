package com.robin.encuentas.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PreguntaDto {

    @NotNull(message = "La descripcion de la pregunta no puede estar en null")
    private String descripcion;
    @NotNull(message = "El tipoRespuesta no puede estar en null")
    private String tipoRespuesta;
    private List<RespuestaDto> respuestas;
}
