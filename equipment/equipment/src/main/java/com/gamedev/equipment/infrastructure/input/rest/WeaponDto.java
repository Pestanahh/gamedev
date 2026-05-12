package com.gamedev.equipment.infrastructure.input.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok: generates getters, setters, toString(), equals(), hashCode()
@AllArgsConstructor // Lombok: generates constructor with all parameters
@NoArgsConstructor // Lombok: generates empty constructor for DTO
public class WeaponDto {

    private Long id;

    @Schema(description = "Weapon name", example = "Excalibur")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Schema(description = "Weapon weight", example = "10")
    @NotNull(message = "Field 'weight' cannot be null")
    private Integer weight;

    @Schema(description = "Weapon attack bonus", example = "15")
    @NotNull(message = "Field 'attackBonus' cannot be null")
    private Integer attackBonus;

    @Schema(description = "Weapon defense bonus", example = "5")
    @NotNull(message = "Field 'defenseBonus' cannot be null")
    private Integer defenseBonus;

    @Schema(description = "Is the weapon magical", example = "true")
    @NotNull(message = "Field 'isMagical' cannot be null")
    private Boolean isMagical;

}
