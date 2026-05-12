package com.gamedev.equipment.infrastructure.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmorJpaRepository extends JpaRepository<ArmorJpa, Long> {

    boolean existsByName(String name);
    boolean existsById(Long id);
}
