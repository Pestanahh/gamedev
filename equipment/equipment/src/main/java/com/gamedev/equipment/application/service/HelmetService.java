package com.gamedev.equipment.application.service;


import com.gamedev.equipment.domain.exception.DuplicateNameException;
import com.gamedev.equipment.domain.exception.EmptyListException;
import com.gamedev.equipment.domain.exception.HelmetNotFoundException;
import com.gamedev.equipment.domain.model.Helmet;
import com.gamedev.equipment.domain.port.input.CreateHelmetUseCase;
import com.gamedev.equipment.domain.port.input.DeleteHelmetUseCase;
import com.gamedev.equipment.domain.port.input.GetAllHelmetsUseCase;
import com.gamedev.equipment.domain.port.input.GetHelmetByIdUseCase;
import com.gamedev.equipment.domain.port.input.UpdateHelmetUseCase;
import com.gamedev.equipment.domain.port.output.HelmetRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HelmetService implements GetAllHelmetsUseCase, GetHelmetByIdUseCase, CreateHelmetUseCase, UpdateHelmetUseCase, DeleteHelmetUseCase {

    private final HelmetRepositoryPort helmetRepositoryPort;

    public List<Helmet> getAllHelmets(){

        List<Helmet> helmets = helmetRepositoryPort.findAll();

        if(helmets.isEmpty())
            throw new EmptyListException();

        return helmets;
    }

    public Helmet getHelmetById(Long id){

        return helmetRepositoryPort.findById(id)
                .orElseThrow(() -> new HelmetNotFoundException(id));
    }

    public Helmet createHelmet(Helmet helmet){

        if(helmetRepositoryPort.existsByName(helmet.getName()))
            throw new DuplicateNameException(helmet.getName());

        return helmetRepositoryPort.save(helmet);

    }

    public Helmet updateHelmet(Helmet helmet, Long id){

        return  helmetRepositoryPort.findById(id)
                .map(existingHelmet -> {
                    existingHelmet.setName(helmet.getName());
                    existingHelmet.setWeight(helmet.getWeight());
                    existingHelmet.setDefenseBonus(helmet.getDefenseBonus());
                    existingHelmet.setFocusBonus(helmet.getFocusBonus());

                    return helmetRepositoryPort.save(existingHelmet);
                }).orElseThrow(() -> new HelmetNotFoundException(id));
    }

    public void deleteHelmet(Long id){
        if(!helmetRepositoryPort.existsById(id))
            throw new HelmetNotFoundException(id);

        helmetRepositoryPort.deleteById(id);
    }


}
