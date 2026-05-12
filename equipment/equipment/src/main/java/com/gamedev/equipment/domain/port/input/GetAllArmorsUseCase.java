package com.gamedev.equipment.domain.port.input;

import com.gamedev.equipment.domain.model.Armor;

import java.util.List;

public interface GetAllArmorsUseCase {

    List<Armor> getAllArmors();

}
