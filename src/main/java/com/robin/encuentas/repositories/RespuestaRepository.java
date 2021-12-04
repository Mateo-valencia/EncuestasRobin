package com.robin.encuentas.repositories;

import com.robin.encuentas.models.RespuestaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RespuestaRepository extends CrudRepository<RespuestaModel,Long> {

    Optional<List<RespuestaModel>> findAllByCdPregunta(Long cdPregunta);
}
