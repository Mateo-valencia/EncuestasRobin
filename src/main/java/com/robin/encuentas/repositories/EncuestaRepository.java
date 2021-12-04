package com.robin.encuentas.repositories;

import com.robin.encuentas.Data.EncuestaData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuestaRepository extends CrudRepository<EncuestaData,Long> {
}
