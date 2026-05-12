package com.gamedev.equipment.domain.port.output;

import com.gamedev.equipment.domain.model.Helmet;

import java.util.List;
import java.util.Optional;

public interface HelmetRepositoryPort {

    List<Helmet> findAll();
    Optional<Helmet> findById(Long id);
    boolean existsById(Long id);
    boolean existsByName(String name);
    Helmet save(Helmet helmet);
    void deleteById(Long id);


}
