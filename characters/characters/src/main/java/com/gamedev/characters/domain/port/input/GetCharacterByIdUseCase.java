package com.gamedev.characters.domain.port.input;


import com.gamedev.characters.domain.model.GameCharacter;

public interface GetCharacterByIdUseCase {

    GameCharacter getCharacterById (Long id);

}
