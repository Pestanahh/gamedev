package com.gamedev.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Equipment {

    List<EquipmentPiece> pieces;
    // This would include Weapon, Armor, Helmet or any other future piece we wanted to add to the equipment
}
