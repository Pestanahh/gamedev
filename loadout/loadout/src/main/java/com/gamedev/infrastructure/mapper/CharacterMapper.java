package com.gamedev.infrastructure.mapper;

import com.gamedev.domain.model.GameCharacter;
import com.gamedev.infrastructure.output.character.CharacterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    GameCharacter fromDtoToDomain (CharacterDTO characterDTO);

}
