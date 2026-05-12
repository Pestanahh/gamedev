package com.gamedev.equipment.infrastructure.output.persistence;

import com.gamedev.equipment.domain.model.Armor;
import com.gamedev.equipment.domain.port.output.ArmorRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArmorPersistenceAdapter implements ArmorRepositoryPort {

    private final ArmorJpaRepository armorJpaRepository;
    private final ArmorPersistenceMapper armorPersistenceMapper;

    @Override
    public List<Armor> findAll(){

        List<ArmorJpa> jpaList = armorJpaRepository.findAll();

        return jpaList.stream().map(jpa -> armorPersistenceMapper.fromJpaToDomain(jpa)).toList();
    }

    @Override
    public Optional<Armor> findById(Long id){
        Optional<ArmorJpa> jpa = armorJpaRepository.findById(id);

        return jpa.map(jpa1 -> armorPersistenceMapper.fromJpaToDomain(jpa1));
    }

    @Override
    public Armor save(Armor armor){
        ArmorJpa jpa = armorPersistenceMapper.fromDomainToJpa(armor);
        ArmorJpa ar = armorJpaRepository.save(jpa);
        return armorPersistenceMapper.fromJpaToDomain(ar);
    }

    @Override
    public boolean existsByName(String name){
        return armorJpaRepository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id){
        return armorJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id){
        armorJpaRepository.deleteById(id);
    }

}
