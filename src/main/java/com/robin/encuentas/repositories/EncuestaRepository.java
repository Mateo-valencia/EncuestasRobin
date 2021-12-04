package com.robin.encuentas.repositories;

import com.robin.encuentas.models.EncuestaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuestaRepository extends CrudRepository<EncuestaModel,Long> {
}
