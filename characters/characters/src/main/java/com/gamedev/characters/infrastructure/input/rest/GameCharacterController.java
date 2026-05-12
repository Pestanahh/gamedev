package com.gamedev.characters.infrastructure.input.rest;

import com.gamedev.characters.domain.model.GameCharacter;
import com.gamedev.characters.domain.port.input.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Characters", description = "Operations over RPG game characters") // Swagger tag
@RestController
@RequestMapping("/characters") // Base endpoint for all character operations
@RequiredArgsConstructor
public class GameCharacterController {

    // Injecting use cases (input ports) and REST mapper
    private final GameCharacterRestMapper gameCharacterRestMapper;

    private final CreateCharacterUseCase createCharacterUseCase;
    private final DeleteCharacterUseCase deleteCharacterUseCase;
    private final GetAllCharactersUseCase getAllCharactersUseCase;
    private final GetCharacterByIdUseCase getCharacterByIdUseCase;
    private final UpdateCharacterUseCase updateCharacterUseCase;
    private final GetCharactersByStatsUseCase getCharactersByStatsUseCase;

    @Operation(summary = "Get all characters", description = "Returns the full list of characters")
    @GetMapping
    public ResponseEntity<List<GameCharacterDto>> getAllCharacters(){

        List<GameCharacter> characters = getAllCharactersUseCase.getAllCharacters();

        List<GameCharacterDto> response = characters
                .stream()
                .map(character -> gameCharacterRestMapper.fromDomainToDto(character)).toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Operation(summary = "Get a character by ID", description = "Returns a single character looked up by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<GameCharacterDto> getCharacterById(@PathVariable Long id){

        GameCharacter character = getCharacterByIdUseCase.getCharacterById(id);
        return ResponseEntity.ok(gameCharacterRestMapper.fromDomainToDto(character));
    }

    @Operation(summary = "Create a character", description = "Adds a new character to the list")
    @PostMapping
    public ResponseEntity<GameCharacterDto> createCharacter(@Valid @RequestBody GameCharacterDto gameCharacterDto){

        GameCharacter character = gameCharacterRestMapper.fromDtoToDomain(gameCharacterDto);
        GameCharacter newCharacter = createCharacterUseCase.createCharacter(character);
        GameCharacterDto response = gameCharacterRestMapper.fromDomainToDto(newCharacter);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a character", description = "Updates a specific character by its ID")
    @PutMapping("/{id}")
    public ResponseEntity<GameCharacterDto> updateCharacter(@Valid @RequestBody GameCharacterDto characterDto, @PathVariable Long id){

        GameCharacter character = gameCharacterRestMapper.fromDtoToDomain(characterDto);
        GameCharacter upToDateCharacter = updateCharacterUseCase.updateCharacter(character, id);
        GameCharacterDto updatedCharacter = gameCharacterRestMapper.fromDomainToDto(upToDateCharacter);

        return new ResponseEntity<>(updatedCharacter, HttpStatus.OK);
    }

    @Operation(summary = "Delete a character", description = "Deletes a specific character by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter (@PathVariable Long id){

        deleteCharacterUseCase.deleteCharacter(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @Operation(summary = "Get a list of certain characters", description = "Get a list of characters from over X health and less than Y defense")
    @GetMapping("/stats")
    public ResponseEntity<List<GameCharacterDto>> getCharacterByStats(@RequestParam Integer health, @RequestParam Integer defense){

        List<GameCharacter> characters = getCharactersByStatsUseCase.getCharactersByStats(health, defense);

        List<GameCharacterDto> response = characters.stream()
                .map(character -> gameCharacterRestMapper.fromDomainToDto(character)).toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
