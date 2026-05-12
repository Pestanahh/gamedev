package com.gamedev.equipment.domain.port.input;

import com.gamedev.equipment.domain.model.Weapon;

import java.util.List;

public interface GetAllWeaponsUseCase {

    List<Weapon> getAllWeapons();

}
