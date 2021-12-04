package com.robin.encuentas.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EncuestaDto {

    @NotNull(message = "El nombre de la encuesta no puede estar en null")
    private String nombre;

    @Min(value = 1,message = "Minimo debe de ir 1 pregunta")
    private Integer nroPreguntas;

    @NotEmpty(message = "Las preguntas no pueden ser vacias")
    private List<PreguntaDto> preguntas;

}
