package com.gamedev.equipment.infrastructure.input.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data // Lombok: generates getters, setters, toString(), equals(), hashCode()
@AllArgsConstructor // Lombok: generates constructor with all parameters
@NoArgsConstructor // Lombok: generates empty constructor for DTO
public class ArmorDto {

    private Long id;

    @Schema(description = "Armor name", example = "Banished Knight Set")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Schema(description = "Armor weight", example = "10")
    @NotNull(message = "Field 'weight' cannot be null")
    private Integer weight;

    @Schema(description = "Armor defense bonus", example = "10")
    @NotNull(message = "Field 'defenseBonus' cannot be null")
    private Integer defenseBonus;

}
