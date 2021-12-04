package com.robin.encuentas.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "t_encuesta")
public class EncuestaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_ENCUESTA")
    private Long id;

    @Column(name = "DS_NOMBRE")
    private String nombre;

    @Column(name = "NM_PREGUNTAS")
    private Integer nroPreguntas;
}
