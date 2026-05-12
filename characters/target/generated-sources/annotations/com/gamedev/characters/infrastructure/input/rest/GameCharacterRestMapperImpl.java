package com.gamedev.characters.infrastructure.input.rest;

import com.gamedev.characters.domain.model.GameCharacter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-20T16:31:05+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class GameCharacterRestMapperImpl implements GameCharacterRestMapper {

    @Override
    public GameCharacterDto fromDomainToDto(GameCharacter gameCharacter) {
        if ( gameCharacter == null ) {
            return null;
        }

        GameCharacterDto gameCharacterDto = new GameCharacterDto();

        gameCharacterDto.setId( gameCharacter.getId() );
        gameCharacterDto.setName( gameCharacter.getName() );
        gameCharacterDto.setHealth( gameCharacter.getHealth() );
        gameCharacterDto.setAttack( gameCharacter.getAttack() );
        gameCharacterDto.setDefense( gameCharacter.getDefense() );
        gameCharacterDto.setSpeed( gameCharacter.getSpeed() );

        return gameCharacterDto;
    }

    @Override
    public GameCharacter fromDtoToDomain(GameCharacterDto gameCharacterDto) {
        if ( gameCharacterDto == null ) {
            return null;
        }

        GameCharacter gameCharacter = new GameCharacter();

        gameCharacter.setId( gameCharacterDto.getId() );
        gameCharacter.setName( gameCharacterDto.getName() );
        gameCharacter.setHealth( gameCharacterDto.getHealth() );
        gameCharacter.setAttack( gameCharacterDto.getAttack() );
        gameCharacter.setDefense( gameCharacterDto.getDefense() );
        gameCharacter.setSpeed( gameCharacterDto.getSpeed() );

        return gameCharacter;
    }
}
