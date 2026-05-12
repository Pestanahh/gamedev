package com.gamedev.infrastructure.input.rest;

import com.gamedev.domain.model.Loadout;
import com.gamedev.domain.port.input.GetLoadoutUseCase;
import com.gamedev.domain.port.input.PostLoadoutUseCase;
import com.gamedev.domain.port.input.UpdateLoadoutUseCase;
import com.gamedev.infrastructure.input.rest.dto.LoadoutInputDTO;
import com.gamedev.infrastructure.input.rest.dto.LoadoutResponseDTO;
import com.gamedev.infrastructure.mapper.LoadoutInputMapper;
import com.gamedev.infrastructure.mapper.LoadoutResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loadout")
@RequiredArgsConstructor
public class LoadoutController {

    private final GetLoadoutUseCase getLoadoutUseCase;
    private final PostLoadoutUseCase postLoadoutUseCase;
    private final UpdateLoadoutUseCase updateLoadoutUseCase;

    private final LoadoutResponseMapper loadoutResponseMapper;
    private final LoadoutInputMapper loadoutInputMapper;

    @GetMapping("/{id}")
    public ResponseEntity<LoadoutResponseDTO> getLoadout(@PathVariable Long id) {
        Loadout loadout = getLoadoutUseCase.getLoadout(id);
        return ResponseEntity.ok(loadoutResponseMapper.toResponse(loadout));
    }

    @PostMapping
    public ResponseEntity<LoadoutResponseDTO> postLoadout(
            @RequestParam Long idCharacter,
            @RequestBody LoadoutInputDTO input) {

        Loadout loadout = postLoadoutUseCase.postLoadout(
                idCharacter,
                loadoutInputMapper.toDomain(input));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loadoutResponseMapper.toResponse(loadout));
    }

    @PutMapping
    public ResponseEntity<LoadoutResponseDTO> putLoadout(
            @RequestParam Long idCharacter,
            @RequestBody LoadoutInputDTO input) {

        Loadout loadout = updateLoadoutUseCase.updateLoadout(
                idCharacter,
                loadoutInputMapper.toDomain(input));
        return ResponseEntity.ok(loadoutResponseMapper.toResponse(loadout));
    }
}
