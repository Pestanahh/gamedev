package com.gamedev.equipment.domain.port.input;

import com.gamedev.equipment.domain.model.Armor;

public interface UpdateArmorUseCase {

    Armor updateArmor (Armor armor, Long id);

}
