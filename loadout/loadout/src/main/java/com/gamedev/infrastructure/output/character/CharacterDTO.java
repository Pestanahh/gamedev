package com.gamedev.infrastructure.output.character;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CharacterDTO {

    private Long id;
    private String name;
    private Integer health;
    private Integer attack;
    private Integer defense;
    private Integer speed;

}
