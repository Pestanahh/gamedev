package com.gamedev.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok: generates getters, setters, toString(), equals(), hashCode()
@NoArgsConstructor // Lombok: generates empty constructor required by JPA
@AllArgsConstructor // Lombok: generates constructor with all parameters

public class Helmet implements EquipmentPiece {

    private Long id;
    private String name;
    private Integer weight;
    private Integer defenseBonus;
    private Integer focusBonus;

    @Override
    public Integer getAttackBonus() {
        return 0;
    }

}
