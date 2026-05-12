package com.gamedev.equipment.infrastructure.output.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "helmet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelmetJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id;

    private String name;
    private Integer weight;
    private Integer defenseBonus;
    private Integer focusBonus;

}
