package com.robin.encuentas.domain;

import com.robin.encuentas.exceptions.MyException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidarDatos {

    public static final String MULTIPLE = "Multiple";
    public static final String ABIERTA = "Abierta";

    public static boolean validarEncuesta(Encuesta encuesta) throws MyException {
        if (encuesta.getNroPreguntas() != encuesta.getPreguntas().size()){
            throw new MyException("El numero de preguntas debe coincidir con el listado de preguntas");
        }
        return validarTipoRespuesta(encuesta);
    }

    private static boolean validarTipoRespuesta(Encuesta encuesta) throws MyException {
        boolean validacionesExitosas = false;
        for (Pregunta pregunta: encuesta.getPreguntas()) {
            if (MULTIPLE.equalsIgnoreCase(pregunta.getTipoRespuesta()) || ABIERTA.equalsIgnoreCase(pregunta.getTipoRespuesta())){
                if (MULTIPLE.equalsIgnoreCase(pregunta.getTipoRespuesta()) && pregunta.getRespuestas().isEmpty()) {
                    throw new MyException("Si el tipo de Respuesta es Multiple debe de engresar al menos 1 respuesta.");
                }
                else if(ABIERTA.equalsIgnoreCase(pregunta.getTipoRespuesta()) && !pregunta.getRespuestas().isEmpty()){
                    throw new MyException("Si el tipo de Respuesta es Abierta No debe contener Respuestas.");
                }else {
                    validacionesExitosas = true;
                }
            }else {
                throw new MyException("Tipo de Respuesta debe ser MULTIPLE O ABIERTA");
            }
        }
        return validacionesExitosas;
    }
}
