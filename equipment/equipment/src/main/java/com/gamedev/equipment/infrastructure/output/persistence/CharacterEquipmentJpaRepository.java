package com.gamedev.equipment.infrastructure.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterEquipmentJpaRepository extends JpaRepository<CharacterEquipmentJpa, Long> {

    boolean existsByCharacterId(Long characterId);
}
