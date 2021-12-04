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
@Table(name = "t_respuesta")
public class RespuestaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_RESPUESTA")
    private Long id;

    @Column(name = "CD_PREGUNTA")
    private Long cdPregunta;

    @Column(name = "DS_RESPUESTA")
    private String descripcion;
}
