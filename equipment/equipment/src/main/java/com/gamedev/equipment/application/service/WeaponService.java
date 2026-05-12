package com.gamedev.equipment.application.service;

import com.gamedev.equipment.domain.exception.DuplicateNameException;
import com.gamedev.equipment.domain.exception.EmptyListException;
import com.gamedev.equipment.domain.exception.HelmetNotFoundException;
import com.gamedev.equipment.domain.exception.WeaponNotFoundException;
import com.gamedev.equipment.domain.model.Weapon;
import com.gamedev.equipment.domain.port.input.*;
import com.gamedev.equipment.domain.port.output.WeaponRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeaponService implements GetWeaponByIdUseCase, GetAllWeaponsUseCase, CreateWeaponByIdUseCase, UpdateWeaponUseCase, DeleteWeaponUseCase {

    private final WeaponRepositoryPort weaponRepositoryPort;

    public Weapon getWeaponById(Long id){

        return weaponRepositoryPort.getById(id)
                .orElseThrow(() -> new HelmetNotFoundException(id));
    }

    public List<Weapon> getAllWeapons(){

        List<Weapon> weapons = weaponRepositoryPort.getAll();

        if(weapons.isEmpty())
            throw new EmptyListException();

        return weapons;
    }

    public Weapon createWeapon(Weapon weapon){

        if(weaponRepositoryPort.existsByName(weapon.getName()))
            throw new DuplicateNameException(weapon.getName());

        return weaponRepositoryPort.save(weapon);
    }

    public Weapon updateWeapon(Weapon weapon, Long id){

        return weaponRepositoryPort.getById(id)
                .map(existingWeapon ->{
                    existingWeapon.setName(weapon.getName());
                    existingWeapon.setWeight(weapon.getWeight());
                    existingWeapon.setAttackBonus(weapon.getAttackBonus());
                    existingWeapon.setDefenseBonus(weapon.getDefenseBonus());
                    existingWeapon.setIsMagical(weapon.getIsMagical());

                    return weaponRepositoryPort.save(existingWeapon);
                })
                .orElseThrow(() -> new WeaponNotFoundException(id));

    }

    public void deleteWeapon(Long id) {

        if(!weaponRepositoryPort.existsById(id))
            throw new WeaponNotFoundException(id);

        weaponRepositoryPort.deleteById(id);
    }
}
