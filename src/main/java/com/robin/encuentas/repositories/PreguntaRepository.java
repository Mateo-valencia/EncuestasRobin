package com.robin.encuentas.repositories;

import com.robin.encuentas.models.PreguntaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreguntaRepository extends CrudRepository<PreguntaModel,Long> {

    Optional<List<PreguntaModel>> findByCdEncuesta(Long cdEncuesta);
}
