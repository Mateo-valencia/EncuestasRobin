package com.robin.encuentas.services;

import com.robin.encuentas.domain.Encuesta;
import com.robin.encuentas.domain.Pregunta;
import com.robin.encuentas.domain.Respuesta;
import com.robin.encuentas.models.EncuestaModel;
import com.robin.encuentas.models.PreguntaModel;
import com.robin.encuentas.models.RespuestaModel;
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

    public void guardar(Encuesta encuesta) {
        EncuestaModel encuestaModel = modelMapper.map(encuesta,EncuestaModel.class);
        encuestaModel = encuestaRepository.save(encuestaModel);

        for (Pregunta pregunta: encuesta.getPreguntas()) {
            PreguntaModel preguntaModel = modelMapper.map(pregunta,PreguntaModel.class);
            preguntaModel.setCdEncuesta(encuestaModel.getId());
            preguntaModel = preguntaRepository.save(preguntaModel);
            for (Respuesta respuesta:pregunta.getRespuestas()) {
                RespuestaModel respuestaModel = modelMapper.map(respuesta,RespuestaModel.class);
                respuestaModel.setCdPregunta(preguntaModel.getId());
                respuestaRepository.save(respuestaModel);
            }
        }
    }

    public Encuesta consultar(Long id){
        Optional<EncuestaModel> encuestaModel = encuestaRepository.findById(id);

        if (encuestaModel.isPresent()){
            Encuesta encuesta = modelMapper.map(encuestaModel.get(),Encuesta.class);
            Optional<List<PreguntaModel>> preguntaModelList = preguntaRepository.findByCdEncuesta(id);

            if (preguntaModelList.isPresent()){
                List<Pregunta> preguntas = new ArrayList<>();
                for (PreguntaModel preguntaModel: preguntaModelList.get()) {
                    Pregunta pregunta = modelMapper.map(preguntaModel,Pregunta.class);
                    if (RESPUESTA_MULTIPLE.equalsIgnoreCase(pregunta.getTipoRespuesta())){
                        Optional<List<RespuestaModel>> respuestaModelList = respuestaRepository.findAllByCdPregunta(preguntaModel.getId());

                        if (respuestaModelList.isPresent()){
                            List<Respuesta> respuestas = new ArrayList<>();
                            for (RespuestaModel respuestaModel:respuestaModelList.get()) {
                                respuestas.add(modelMapper.map(respuestaModel,Respuesta.class));
                            }
                            pregunta.setRespuestas(respuestas);
                        }
                    }
                    preguntas.add(pregunta);
                }
                encuesta.setPreguntas(preguntas);
            }
            return encuesta;
        }else{
            return null;
        }
    }
}
