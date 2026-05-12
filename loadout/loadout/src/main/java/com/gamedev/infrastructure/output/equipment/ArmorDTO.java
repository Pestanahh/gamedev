package com.gamedev.infrastructure.output.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArmorDTO implements EquipmentPieceDTO {

    private Long id;
    private String name;
    private Integer weight;
    private Integer defenseBonus;

}
