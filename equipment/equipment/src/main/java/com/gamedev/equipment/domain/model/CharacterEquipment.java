package com.gamedev.equipment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterEquipment {


    private Long id;
    private Long characterId;
    private Long armorId;
    private Long helmetId;
    private Long weaponId;
}
