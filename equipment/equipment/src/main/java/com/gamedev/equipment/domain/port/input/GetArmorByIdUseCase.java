package com.gamedev.equipment.domain.port.input;

import com.gamedev.equipment.domain.model.Armor;

public interface GetArmorByIdUseCase {

    Armor getArmorById(Long id);

}
