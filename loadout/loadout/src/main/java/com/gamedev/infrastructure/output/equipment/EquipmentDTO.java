package com.gamedev.infrastructure.output.equipment;

import com.gamedev.domain.model.Armor;
import com.gamedev.domain.model.Helmet;
import com.gamedev.domain.model.Weapon;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EquipmentDTO {
    Weapon weapon;
    Armor armor;
    Helmet helmet;
}
