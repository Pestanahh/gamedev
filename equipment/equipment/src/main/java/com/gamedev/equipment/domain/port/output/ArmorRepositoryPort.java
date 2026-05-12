package com.gamedev.equipment.domain.port.output;

import com.gamedev.equipment.domain.model.Armor;

import java.util.List;
import java.util.Optional;

public interface ArmorRepositoryPort {

    List<Armor> findAll();
    Optional<Armor> findById(Long id);
    boolean existsByName(String name);
    boolean existsById(Long id);
    Armor save(Armor armor);
    void deleteById(Long id);

}
