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
public class HelmetDto {

    private Long id;

    @Schema(description = "Helmet name", example = "Great Helm")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Schema(description = "Helmet weight", example = "5")
    @NotNull(message = "Field 'weight' cannot be null")
    private Integer weight;

    @Schema(description = "Helmet defense bonus", example = "8")
    @NotNull(message = "Field 'defenseBonus' cannot be null")
    private Integer defenseBonus;

    @Schema(description = "Helmet focus bonus", example = "3")
    @NotNull(message = "Field 'focusBonus' cannot be null")
    private Integer focusBonus;

}
