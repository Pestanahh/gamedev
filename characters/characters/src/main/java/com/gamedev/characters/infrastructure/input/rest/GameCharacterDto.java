package com.gamedev.characters.infrastructure.input.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok: generates getters, setters, toString(), equals(), hashCode()
@AllArgsConstructor // Lombok: generates constructor with all parameters
@NoArgsConstructor // Lombok: generates empty constructor for DTO
public class GameCharacterDto {

    private Long id;

    @Schema(description = "Character name", example = "Butcher")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Schema(description = "Character health points", example = "100")
    @NotNull(message = "Field 'health' cannot be null")
    private Integer health;

    @Schema(description = "Character attack stat", example = "10")
    @NotNull(message = "Field 'attack' cannot be null")
    private Integer attack;

    @Schema(description = "Character defense stat", example = "10")
    @NotNull(message = "Field 'defense' cannot be null")
    private Integer defense;

    @Schema(description = "Character speed stat", example = "10")
    @NotNull(message = "Field 'speed' cannot be null")
    private Integer speed;

}
