package com.gamedev.equipment.infrastructure.input.rest;

import com.gamedev.equipment.domain.model.CharacterEquipment;
import com.gamedev.equipment.domain.port.input.AssignEquipmentToCharacterUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
@Tag(name = "Character Equipment", description = "Assign equipment to characters")
public class CharacterEquipmentController {

    private final AssignEquipmentToCharacterUseCase assignEquipmentToCharacterUseCase;
    private final CharacterEquipmentRestMapper characterEquipmentRestMapper;

    @PostMapping("/{id}/equipment")
    @Operation(summary = "Assign equipment to a character", description = "Assigns armor, helmet and weapon to a character by character ID")
    public ResponseEntity<CharacterEquipmentDto> assignEquipment(
            @PathVariable Long id,
            @Valid @RequestBody CharacterEquipmentDto characterEquipmentDto) {

        CharacterEquipment domain = characterEquipmentRestMapper.fromDtoToDomain(characterEquipmentDto);
        CharacterEquipment saved = assignEquipmentToCharacterUseCase.assignEquipmentToCharacter(id, domain);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(characterEquipmentRestMapper.fromDomainToDto(saved));
    }
}
