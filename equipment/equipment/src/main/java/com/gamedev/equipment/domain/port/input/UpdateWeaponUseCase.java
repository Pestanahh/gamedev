package com.gamedev.equipment.domain.port.input;

import com.gamedev.equipment.domain.model.Weapon;

public interface UpdateWeaponUseCase {

    Weapon updateWeapon (Weapon weapon, Long id);

}
