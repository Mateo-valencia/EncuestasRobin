package com.robin.encuentas.repositories;

import com.robin.encuentas.Data.RespuestaData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RespuestaRepository extends CrudRepository<RespuestaData,Long> {

    Optional<List<RespuestaData>> findAllByCdPregunta(Long cdPregunta);
}
