package com.dosw.masterchef.repository;

import com.dosw.masterchef.model.Receta;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RecetaRepository extends MongoRepository<Receta, String> {
    List<Receta> findByTipoChef(String tipoChef);
    List<Receta> findByTemporada(Integer temporada);
    List<Receta> findByIngredientesContainingIgnoreCase(String ingrediente);

}
