package com.robin.encuentas.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "t_pregunta")
public class PreguntaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_PREGUNTA")
    private Long id;

    @Column(name = "CD_ENCUESTA")
    private Long cdEncuesta;

    @Column(name = "DS_PREGUNTA")
    private String descripcion;

    @Column(name = "TP_RESPUESTA")
    private String tipoRespuesta;
}

