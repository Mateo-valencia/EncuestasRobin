package com.robin.encuentas.controllers;

import com.robin.encuentas.domain.Encuesta;
import com.robin.encuentas.domain.ValidarDatos;
import com.robin.encuentas.dto.EncuestaDto;
import com.robin.encuentas.exceptions.MyException;
import com.robin.encuentas.services.EncuestaService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/encuesta")
@AllArgsConstructor
public class EncuestaController {

    private final Logger logger = LoggerFactory.getLogger(EncuestaController.class);

    private final EncuestaService encuestaService;
    private final ModelMapper modelMapper;

    @PostMapping
    public String save(@Valid @RequestBody EncuestaDto encuestaDto){
        Encuesta encuesta = modelMapper.map(encuestaDto, Encuesta.class);
        try{
            if (ValidarDatos.validarEncuesta(encuesta)){
                encuestaService.guardar(encuesta);
            }else {
                return "Ocurrio un Error al Guardar La encuesta";
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            return "Ocurrio un Error al Guardar La encuesta:" + e.getMessage();
        }
        return "Encuesta Guardada con Exito.";
    }

    @GetMapping("/obtener")
    public EncuestaDto get(@RequestParam Long id) throws MyException {
        Encuesta encuesta = encuestaService.consultar(id);
        if(Objects.nonNull(encuesta)){
            return modelMapper.map(encuesta,EncuestaDto.class);
        }else {
            throw new MyException("No fue posible encontrar La Encuesta.");
        }
    }
}
