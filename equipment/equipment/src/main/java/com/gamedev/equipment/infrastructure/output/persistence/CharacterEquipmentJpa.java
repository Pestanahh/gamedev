package com.gamedev.equipment.infrastructure.output.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "character_equipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterEquipmentJpa {

    @Id
    private Long characterId;
    private Long armorId;
    private Long helmetId;
    private Long weaponId;
}
