package com.gamedev.characters.infrastructure.input.rest;

import com.gamedev.characters.domain.model.GameCharacter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameCharacterRestMapper {
    GameCharacterDto fromDomainToDto (GameCharacter gameCharacter);
    GameCharacter fromDtoToDomain (GameCharacterDto gameCharacterDto);
}
