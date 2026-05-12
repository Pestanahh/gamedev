package com.gamedev.characters.infrastructure.output.persistence;

import com.gamedev.characters.domain.model.GameCharacter;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-20T16:31:05+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class GameCharacterPersistenceMapperImpl implements GameCharacterPersistenceMapper {

    @Override
    public GameCharacterJpa fromDomainToJpa(GameCharacter ch) {
        if ( ch == null ) {
            return null;
        }

        GameCharacterJpa gameCharacterJpa = new GameCharacterJpa();

        gameCharacterJpa.setId( ch.getId() );
        gameCharacterJpa.setName( ch.getName() );
        gameCharacterJpa.setHealth( ch.getHealth() );
        gameCharacterJpa.setAttack( ch.getAttack() );
        gameCharacterJpa.setDefense( ch.getDefense() );
        gameCharacterJpa.setSpeed( ch.getSpeed() );

        return gameCharacterJpa;
    }

    @Override
    public GameCharacter fromJpaToDomain(GameCharacterJpa jpa) {
        if ( jpa == null ) {
            return null;
        }

        GameCharacter gameCharacter = new GameCharacter();

        gameCharacter.setId( jpa.getId() );
        gameCharacter.setName( jpa.getName() );
        gameCharacter.setHealth( jpa.getHealth() );
        gameCharacter.setAttack( jpa.getAttack() );
        gameCharacter.setDefense( jpa.getDefense() );
        gameCharacter.setSpeed( jpa.getSpeed() );

        return gameCharacter;
    }
}
