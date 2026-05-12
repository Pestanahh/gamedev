package com.gamedev.characters.application.service;

import com.gamedev.characters.domain.port.input.*;
import com.gamedev.characters.domain.port.output.CharacterRepositoryPort;
import com.gamedev.characters.domain.exception.DuplicateNameException;
import com.gamedev.characters.domain.exception.EmptyListException;
import com.gamedev.characters.domain.exception.GameCharacterNotFoundException;
import com.gamedev.characters.domain.model.GameCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService implements CreateCharacterUseCase, DeleteCharacterUseCase, GetCharacterByIdUseCase, GetAllCharactersUseCase, UpdateCharacterUseCase, GetCharactersByStatsUseCase {

    private final CharacterRepositoryPort characterRepositoryPort;

    public GameCharacter getCharacterById(Long id){

        return characterRepositoryPort.findById(id)
                .orElseThrow(()-> new GameCharacterNotFoundException(id));

    }

    public List<GameCharacter> getAllCharacters(){

        List<GameCharacter> characters = characterRepositoryPort.findAll();

        if(characters.isEmpty())
            throw new EmptyListException();

        return characters;

    }

    public GameCharacter createCharacter(GameCharacter gameCharacter){

        if(characterRepositoryPort.existsByName(gameCharacter.getName()))
            throw new DuplicateNameException(gameCharacter.getName());

        return characterRepositoryPort.save(gameCharacter);
    }

    public GameCharacter updateCharacter(GameCharacter gameCharacter, Long id){

        return characterRepositoryPort.findById(id)
                .map(existingCharacter -> {
                    existingCharacter.setName(gameCharacter.getName());
                    existingCharacter.setAttack(gameCharacter.getAttack());
                    existingCharacter.setDefense(gameCharacter.getDefense());
                    existingCharacter.setSpeed(gameCharacter.getSpeed());
                    existingCharacter.setHealth(gameCharacter.getHealth());

                    return characterRepositoryPort.save(existingCharacter);
                })
                .orElseThrow(() -> new GameCharacterNotFoundException(id));

    }

    public void deleteCharacter(Long id){

        if(!characterRepositoryPort.existsById(id))
            throw new GameCharacterNotFoundException(id);
        characterRepositoryPort.deleteById(id);

    }

    public List<GameCharacter> getCharactersByStats (Integer health, Integer defense){

        List<GameCharacter> characters = characterRepositoryPort.findByStats(health, defense);

        if(characters.isEmpty())
            throw new EmptyListException();

            return characters;

    }
}
