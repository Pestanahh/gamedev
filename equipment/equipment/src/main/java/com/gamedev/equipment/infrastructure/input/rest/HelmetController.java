package com.gamedev.equipment.infrastructure.input.rest;

import com.gamedev.equipment.domain.model.Helmet;
import com.gamedev.equipment.domain.port.input.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Helmets", description = "Operations over RPG helmets") // Swagger tag
@RestController
@RequestMapping("/helmets") // Base endpoint for all helmet operations
@RequiredArgsConstructor
public class HelmetController {

    // Injecting use cases (input ports) and REST mapper
    private final HelmetRestMapper helmetRestMapper;

    private final CreateHelmetUseCase createHelmetUseCase;
    private final DeleteHelmetUseCase deleteHelmetUseCase;
    private final GetHelmetByIdUseCase getHelmetByIdUseCase;
    private final GetAllHelmetsUseCase getAllHelmetsUseCase;
    private final UpdateHelmetUseCase updateHelmetUseCase;

    @Operation(summary = "Get all helmets", description = "Returns the full list of helmets")
    @GetMapping
    public ResponseEntity<List<HelmetDto>> getAllHelmets(){

        List<Helmet> helmets = getAllHelmetsUseCase.getAllHelmets();

        List<HelmetDto> response = helmets
                .stream()
                .map(helmet -> helmetRestMapper.fromDomainToDto(helmet)).toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get helmet by ID", description = "Returns a single helmet by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<HelmetDto> getHelmetById(@PathVariable Long id){

        Helmet helmet = getHelmetByIdUseCase.getHelmetById(id);

        HelmetDto response = helmetRestMapper.fromDomainToDto(helmet);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Create a new helmet", description = "Creates a new helmet and returns it")
    @PostMapping
    public ResponseEntity<HelmetDto> createHelmet(@Valid @RequestBody HelmetDto helmetDto){

        Helmet helmet = helmetRestMapper.fromDtoToDomain(helmetDto);

        Helmet createdHelmet = createHelmetUseCase.createHelmet(helmet);

        HelmetDto response = helmetRestMapper.fromDomainToDto(createdHelmet);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing helmet", description = "Updates a helmet by its ID and returns the updated helmet")
    @PutMapping("/{id}")
    public ResponseEntity<HelmetDto> updateHelmet(@PathVariable Long id, @Valid @RequestBody HelmetDto helmetDto){

        Helmet helmet = helmetRestMapper.fromDtoToDomain(helmetDto);

        Helmet updatedHelmet = updateHelmetUseCase.updateHelmet(helmet, id);

        HelmetDto response = helmetRestMapper.fromDomainToDto(updatedHelmet);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a helmet", description = "Deletes a helmet by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHelmet(@PathVariable Long id){

        deleteHelmetUseCase.deleteHelmet(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
