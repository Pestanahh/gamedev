package com.gamedev.characters.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok: generates getters, setters, toString(), equals(), hashCode()
@NoArgsConstructor // Lombok: generates empty constructor
@AllArgsConstructor // Lombok: generates constructor with all parameters
public class GameCharacter {

    private Long id;
    private String name;
    private Integer health;
    private Integer attack;
    private Integer defense;
    private Integer speed;

}
