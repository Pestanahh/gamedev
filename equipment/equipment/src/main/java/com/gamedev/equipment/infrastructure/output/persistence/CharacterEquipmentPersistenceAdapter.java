package com.gamedev.equipment.infrastructure.output.persistence;

import com.gamedev.equipment.domain.model.CharacterEquipment;
import com.gamedev.equipment.domain.port.output.CharacterEquipmentRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CharacterEquipmentPersistenceAdapter implements CharacterEquipmentRepositoryPort {

    private final CharacterEquipmentJpaRepository characterEquipmentJpaRepository;
    private final CharacterEquipmentPersistenceMapper characterEquipmentPersistenceMapper;

    @Override
    public CharacterEquipment save(CharacterEquipment characterEquipment) {
        CharacterEquipmentJpa jpa = characterEquipmentPersistenceMapper.fromDomainToJpa(characterEquipment);
        CharacterEquipmentJpa saved = characterEquipmentJpaRepository.save(jpa);
        return characterEquipmentPersistenceMapper.fromJpaToDomain(saved);
    }

    @Override
    public Optional<CharacterEquipment> findByCharacterId(Long characterId) {
        return characterEquipmentJpaRepository.findById(characterId)
                .map(jpa -> characterEquipmentPersistenceMapper.fromJpaToDomain(jpa));
    }

    @Override
    public boolean existsByCharacterId(Long characterId) {
        return characterEquipmentJpaRepository.existsByCharacterId(characterId);
    }
}
