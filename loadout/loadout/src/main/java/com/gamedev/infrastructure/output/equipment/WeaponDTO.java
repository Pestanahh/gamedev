package com.gamedev.infrastructure.output.equipment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeaponDTO implements EquipmentPieceDTO{

    private Long id;
    private String name;
    private Integer weight;
    private Integer attackBonus;
    private Integer defenseBonus;
    private Boolean isMagical;

}
