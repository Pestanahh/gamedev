package com.gamedev.equipment.infrastructure.input.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterEquipmentDto {

    private Long id;

    private Long characterId;

    @Schema(description = "Armor ID to assign", example = "1")
    @NotNull(message = "Field 'armorId' cannot be null")
    private Long armorId;

    @Schema(description = "Helmet ID to assign", example = "1")
    @NotNull(message = "Field 'helmetId' cannot be null")
    private Long helmetId;

    @Schema(description = "Weapon ID to assign", example = "1")
    @NotNull(message = "Field 'weaponId' cannot be null")
    private Long weaponId;
}
