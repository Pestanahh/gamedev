package com.gamedev.characters.domain.port.input;

import com.gamedev.characters.domain.model.GameCharacter;

public interface CreateCharacterUseCase {

    GameCharacter createCharacter (GameCharacter gameCharacter);

}
