package com.robin.encuentas.repositories;

import com.robin.encuentas.Data.PreguntaData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreguntaRepository extends CrudRepository<PreguntaData,Long> {

    Optional<List<PreguntaData>> findByCdEncuesta(Long cdEncuesta);
}
