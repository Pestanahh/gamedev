package com.gamedev.equipment.infrastructure.input.rest;

import com.gamedev.equipment.domain.model.Armor;
import com.gamedev.equipment.domain.port.input.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Armors", description = "Operations over RPG armors") // Swagger tag
@RestController
@RequestMapping("/armors") // Base endpoint for all character operations
@RequiredArgsConstructor
public class ArmorController {

    // Injecting use cases (input ports) and REST mapper
    private final ArmorRestMapper armorRestMapper;

    private final CreateArmorUseCase createArmorUseCase;
    private final DeleteArmorUseCase deleteArmorUseCase;
    private final GetArmorByIdUseCase getArmorByIdUseCase;
    private final GetAllArmorsUseCase getAllArmorsUseCase;
    private final UpdateArmorUseCase updateArmorUseCase;

    @Operation(summary = "Get all armors", description = "Returns the full list of armors")
    @GetMapping
    public ResponseEntity<List<ArmorDto>> getAllArmors(){

        List<Armor> armors = getAllArmorsUseCase.getAllArmors();

        List<ArmorDto> response = armors
                .stream()
                .map(armor -> armorRestMapper.fromDomainToDto(armor)).toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get armor by ID", description = "Returns a single armor by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ArmorDto> getArmorById(@PathVariable Long id){

        Armor armor = getArmorByIdUseCase.getArmorById(id);

        ArmorDto response = armorRestMapper.fromDomainToDto(armor);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Create a new armor", description = "Creates a new armor and returns it")
    @PostMapping
    public ResponseEntity<ArmorDto> createArmor(@Valid @RequestBody ArmorDto armorDto){

        Armor armor = armorRestMapper.fromDtoToDomain(armorDto);

        Armor createdArmor = createArmorUseCase.createArmor(armor);

        ArmorDto response = armorRestMapper.fromDomainToDto(createdArmor);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing armor", description = "Updates an armor by its ID and returns the updated armor")
    @PutMapping("/{id}")
    public ResponseEntity<ArmorDto> updateArmor(@PathVariable Long id, @Valid @RequestBody ArmorDto armorDto){

        Armor armor = armorRestMapper.fromDtoToDomain(armorDto);

        Armor updatedArmor = updateArmorUseCase.updateArmor(armor, id);

        ArmorDto response = armorRestMapper.fromDomainToDto(updatedArmor);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete an armor", description = "Deletes an armor by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArmor(@PathVariable Long id){

        deleteArmorUseCase.deleteArmor(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
