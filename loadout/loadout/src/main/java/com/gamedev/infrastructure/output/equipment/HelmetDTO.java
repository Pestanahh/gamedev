package com.gamedev.infrastructure.output.equipment;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Normalized;

@Data
@NoArgsConstructor
public class HelmetDTO implements EquipmentPieceDTO{

    private Long id;
    private String name;
    private Integer weight;
    private Integer defenseBonus;
    private Integer focusBonus;
}
