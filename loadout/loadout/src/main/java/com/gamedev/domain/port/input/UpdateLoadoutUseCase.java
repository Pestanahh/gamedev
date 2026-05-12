package com.gamedev.domain.port.input;

import com.gamedev.domain.model.Loadout;
import com.gamedev.domain.model.LoadoutInput;

public interface UpdateLoadoutUseCase {
    Loadout updateLoadout (Long idCharacter, LoadoutInput request);
}
