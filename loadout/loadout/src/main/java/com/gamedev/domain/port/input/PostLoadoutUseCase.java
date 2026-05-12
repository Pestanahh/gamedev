package com.gamedev.domain.port.input;

import com.gamedev.domain.model.Loadout;
import com.gamedev.domain.model.LoadoutInput;

public interface PostLoadoutUseCase {
    Loadout postLoadout (Long idCharacter, LoadoutInput request);
}
