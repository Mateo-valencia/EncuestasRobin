package com.robin.encuentas.Data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
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
