package com.gamedev.characters.infrastructure.output.persistence;


import com.gamedev.characters.domain.model.GameCharacter;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameCharacterPersistenceMapper {

    GameCharacterJpa fromDomainToJpa (GameCharacter ch);
    GameCharacter fromJpaToDomain (GameCharacterJpa jpa);

}
