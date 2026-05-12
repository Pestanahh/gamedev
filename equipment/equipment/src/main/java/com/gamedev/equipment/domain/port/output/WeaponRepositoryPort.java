package com.gamedev.equipment.domain.port.output;

import com.gamedev.equipment.domain.model.Weapon;

import java.util.List;
import java.util.Optional;

public interface WeaponRepositoryPort {

    List<Weapon> getAll();
    Optional<Weapon> getById(Long id);
    boolean existsById(Long id);
    boolean existsByName(String name);
    Weapon save(Weapon weapon);
    void deleteById(Long id);

}
