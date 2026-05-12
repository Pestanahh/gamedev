package com.gamedev.equipment.infrastructure.output.persistence;

import com.gamedev.equipment.domain.model.Weapon;
import com.gamedev.equipment.domain.port.output.WeaponRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WeaponPersistenceAdapter implements WeaponRepositoryPort {

    private final WeaponJpaRepository weaponJpaRepository;
    private final WeaponPersistenceMapper weaponPersistenceMapper;

    @Override
    public List<Weapon> getAll(){

        List<WeaponJpa> jpaList = weaponJpaRepository.findAll();

        return jpaList.stream().map(jpa -> weaponPersistenceMapper.fromJpaToDomain(jpa)).toList();
    }

    @Override
    public Optional<Weapon> getById(Long id){
        Optional<WeaponJpa> jpa = weaponJpaRepository.findById(id);

        return jpa.map(jpa1 -> weaponPersistenceMapper.fromJpaToDomain(jpa1));
    }

    @Override
    public Weapon save(Weapon weapon){
        WeaponJpa jpa = weaponPersistenceMapper.fromDomainToJpa(weapon);
        WeaponJpa ar = weaponJpaRepository.save(jpa);
        return weaponPersistenceMapper.fromJpaToDomain(ar);
    }

    @Override
    public boolean existsByName(String name){
        return weaponJpaRepository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id){
        return weaponJpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id){
        weaponJpaRepository.deleteById(id);
    }

}
