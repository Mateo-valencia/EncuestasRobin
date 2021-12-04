package com.robin.encuentas.Data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "t_encuesta")
public class EncuestaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_ENCUESTA")
    private Long id;

    @Column(name = "DS_NOMBRE")
    private String nombre;

    @Column(name = "NM_PREGUNTAS")
    private Integer nroPreguntas;
}
