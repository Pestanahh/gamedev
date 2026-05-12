package com.gamedev.characters.domain.port.input;

import com.gamedev.characters.domain.model.GameCharacter;

public interface UpdateCharacterUseCase {

    GameCharacter updateCharacter (GameCharacter gameCharacter, Long id);

}
