package com.gamedev.equipment.infrastructure.output.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "weapon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeaponJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id;

    private String name;
    private Integer weight;
    private Integer attackBonus;
    private Integer defenseBonus;
    private Boolean isMagical;

}
