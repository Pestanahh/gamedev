package com.gamedev.equipment.application.service;

import com.gamedev.equipment.domain.exception.ArmorNotFoundException;
import com.gamedev.equipment.domain.exception.DuplicateNameException;
import com.gamedev.equipment.domain.exception.EmptyListException;
import com.gamedev.equipment.domain.model.Armor;
import com.gamedev.equipment.domain.port.input.*;
import com.gamedev.equipment.domain.port.output.ArmorRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArmorService implements CreateArmorUseCase, DeleteArmorUseCase, UpdateArmorUseCase, GetAllArmorsUseCase, GetArmorByIdUseCase {

    private final ArmorRepositoryPort armorRepositoryPort;

    public Armor getArmorById(Long id){

        return armorRepositoryPort.findById(id)
                .orElseThrow(() -> new ArmorNotFoundException(id));
    }

    public List<Armor> getAllArmors(){
        List<Armor> armors = armorRepositoryPort.findAll();

        if(armors.isEmpty())
            throw new EmptyListException();

        return armors;
    }

    public Armor createArmor(Armor armor){

        if(armorRepositoryPort.existsByName(armor.getName()))
            throw new DuplicateNameException(armor.getName());

        return armorRepositoryPort.save(armor);
    }

    public Armor updateArmor(Armor armor, Long id){

        return armorRepositoryPort.findById(id)
                .map(existingArmor -> {
                    existingArmor.setName(armor.getName());
                    existingArmor.setWeight(armor.getWeight());
                    existingArmor.setDefenseBonus(armor.getDefenseBonus());

                    return armorRepositoryPort.save(existingArmor);
                })
                .orElseThrow(() -> new ArmorNotFoundException(id));

    }

    public void deleteArmor(Long id){
        if(!armorRepositoryPort.existsById(id))
            throw new ArmorNotFoundException(id);

        armorRepositoryPort.deleteById(id);
    }

}




