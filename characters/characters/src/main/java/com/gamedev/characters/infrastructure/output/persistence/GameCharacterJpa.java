package com.gamedev.characters.infrastructure.output.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "game_character")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameCharacterJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id;

    private String name;
    private Integer health;
    private Integer attack;
    private Integer defense;
    private Integer speed;

}
