package com.gamedev.equipment.infrastructure.output.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "armor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArmorJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id;

    private String name;
    private Integer weight;
    private Integer defenseBonus;

}
