package com.gamedev.equipment.domain.port.input;

import com.gamedev.equipment.domain.model.Weapon;

public interface CreateWeaponByIdUseCase {

    Weapon createWeapon (Weapon weapon);

}
