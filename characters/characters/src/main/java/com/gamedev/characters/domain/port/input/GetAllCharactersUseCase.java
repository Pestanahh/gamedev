package com.gamedev.characters.domain.port.input;

import com.gamedev.characters.domain.model.GameCharacter;

import java.util.List;

public interface GetAllCharactersUseCase {

    List<GameCharacter> getAllCharacters();

}
