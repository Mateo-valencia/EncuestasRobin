package com.robin.encuentas.services;

import com.robin.encuentas.domain.Encuesta;
import com.robin.encuentas.domain.Pregunta;
import com.robin.encuentas.domain.Respuesta;
import com.robin.encuentas.Data.EncuestaData;
import com.robin.encuentas.Data.PreguntaData;
import com.robin.encuentas.Data.RespuestaData;
import com.robin.encuentas.repositories.EncuestaRepository;
import com.robin.encuentas.repositories.PreguntaRepository;
import com.robin.encuentas.repositories.RespuestaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EncuestaService {

    public static final String RESPUESTA_MULTIPLE = "Multiple";
    private final EncuestaRepository encuestaRepository;
    private final PreguntaRepository preguntaRepository;
    private final RespuestaRepository respuestaRepository;
    private final ModelMapper modelMapper;

    public Long guardar(Encuesta encuesta) {
        EncuestaData encuestaData = modelMapper.map(encuesta, EncuestaData.class);
        encuestaData = encuestaRepository.save(encuestaData);

        for (Pregunta pregunta: encuesta.getPreguntas()) {
            PreguntaData preguntaData = modelMapper.map(pregunta, PreguntaData.class);
            preguntaData.setCdEncuesta(encuestaData.getId());
            preguntaData = preguntaRepository.save(preguntaData);
            for (Respuesta respuesta:pregunta.getRespuestas()) {
                RespuestaData respuestaData = modelMapper.map(respuesta, RespuestaData.class);
                respuestaData.setCdPregunta(preguntaData.getId());
                respuestaRepository.save(respuestaData);
            }
        }
        return encuestaData.getId();
    }

    public Encuesta consultar(Long id){
        Optional<EncuestaData> encuestaData = encuestaRepository.findById(id);
        if (encuestaData.isPresent()){
            Encuesta encuesta = modelMapper.map(encuestaData.get(),Encuesta.class);
            Optional<List<PreguntaData>> preguntaModelList = preguntaRepository.findByCdEncuesta(id);

            return consultarRespuestas(encuesta,preguntaModelList);
        }else{
            return null;
        }
    }

    private Encuesta consultarRespuestas(Encuesta encuesta, Optional<List<PreguntaData>> preguntaModelList) {
       if (preguntaModelList.isPresent()){
           List<Pregunta> preguntas = new ArrayList<>();
           for (PreguntaData preguntaData : preguntaModelList.get()) {
               Pregunta pregunta = modelMapper.map(preguntaData,Pregunta.class);
               preguntas.add(consultarRespuestas(preguntaData, pregunta));
           }
           encuesta.setPreguntas(preguntas);
           return encuesta;
       }else {
           return null;
       }
    }

    private Pregunta consultarRespuestas(PreguntaData preguntaData, Pregunta pregunta) {
        if (RESPUESTA_MULTIPLE.equalsIgnoreCase(pregunta.getTipoRespuesta())){
            Optional<List<RespuestaData>> respuestaModelList = respuestaRepository.findAllByCdPregunta(preguntaData.getId());

            if (respuestaModelList.isPresent()){
                List<Respuesta> respuestas = new ArrayList<>();
                for (RespuestaData respuestaData :respuestaModelList.get()) {
                    respuestas.add(modelMapper.map(respuestaData,Respuesta.class));
                }
                pregunta.setRespuestas(respuestas);
            }
        }
        return pregunta;
    }
}
