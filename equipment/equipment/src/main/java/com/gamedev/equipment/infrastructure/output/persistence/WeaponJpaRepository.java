package com.gamedev.equipment.infrastructure.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponJpaRepository extends JpaRepository<WeaponJpa, Long> {

    boolean existsByName(String name);
    boolean existsById(Long id);
}
