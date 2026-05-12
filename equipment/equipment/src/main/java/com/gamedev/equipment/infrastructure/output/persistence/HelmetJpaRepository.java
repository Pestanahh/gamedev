package com.gamedev.equipment.infrastructure.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HelmetJpaRepository extends JpaRepository<HelmetJpa, Long> {

    boolean existsByName(String name);
    boolean existsById(Long id);
}
