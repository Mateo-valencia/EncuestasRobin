package com.robin.encuentas.Data;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "t_pregunta")
public class PreguntaData {

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

