package com.gamedev.equipment.infrastructure.input.rest;

import com.gamedev.equipment.domain.model.Weapon;
import com.gamedev.equipment.domain.port.input.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Weapons", description = "Operations over RPG weapons") // Swagger tag
@RestController
@RequestMapping("/weapons") // Base endpoint for all weapon operations
@RequiredArgsConstructor
public class WeaponController {

    // Injecting use cases (input ports) and REST mapper
    private final WeaponRestMapper weaponRestMapper;

    private final CreateWeaponByIdUseCase createWeaponByIdUseCase;
    private final DeleteWeaponUseCase deleteWeaponUseCase;
    private final GetWeaponByIdUseCase getWeaponByIdUseCase;
    private final GetAllWeaponsUseCase getAllWeaponsUseCase;
    private final UpdateWeaponUseCase updateWeaponUseCase;

    @Operation(summary = "Get all weapons", description = "Returns the full list of weapons")
    @GetMapping
    public ResponseEntity<List<WeaponDto>> getAllWeapons(){

        List<Weapon> weapons = getAllWeaponsUseCase.getAllWeapons();

        List<WeaponDto> response = weapons
                .stream()
                .map(weapon -> weaponRestMapper.fromDomainToDto(weapon)).toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get weapon by ID", description = "Returns a single weapon by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<WeaponDto> getWeaponById(@PathVariable Long id){

        Weapon weapon = getWeaponByIdUseCase.getWeaponById(id);

        WeaponDto response = weaponRestMapper.fromDomainToDto(weapon);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Create a new weapon", description = "Creates a new weapon and returns it")
    @PostMapping
    public ResponseEntity<WeaponDto> createWeapon(@Valid @RequestBody WeaponDto weaponDto){

        Weapon weapon = weaponRestMapper.fromDtoToDomain(weaponDto);

        Weapon createdWeapon = createWeaponByIdUseCase.createWeapon(weapon);

        WeaponDto response = weaponRestMapper.fromDomainToDto(createdWeapon);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing weapon", description = "Updates a weapon by its ID and returns the updated weapon")
    @PutMapping("/{id}")
    public ResponseEntity<WeaponDto> updateWeapon(@PathVariable Long id, @Valid @RequestBody WeaponDto weaponDto){

        Weapon weapon = weaponRestMapper.fromDtoToDomain(weaponDto);

        Weapon updatedWeapon = updateWeaponUseCase.updateWeapon(weapon, id);

        WeaponDto response = weaponRestMapper.fromDomainToDto(updatedWeapon);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a weapon", description = "Deletes a weapon by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeapon(@PathVariable Long id){

        deleteWeaponUseCase.deleteWeapon(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
