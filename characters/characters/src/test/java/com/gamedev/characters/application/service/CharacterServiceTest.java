package com.gamedev.characters.application.service;

import com.gamedev.characters.domain.exception.DuplicateNameException;
import com.gamedev.characters.domain.exception.EmptyListException;
import com.gamedev.characters.domain.exception.GameCharacterNotFoundException;
import com.gamedev.characters.domain.model.GameCharacter;
import com.gamedev.characters.domain.port.output.CharacterRepositoryPort;
import com.gamedev.characters.infrastructure.output.persistence.GameCharacterJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {

    @Mock
    private CharacterRepositoryPort characterRepositoryPort;

    @InjectMocks
    private CharacterService characterService;

    @Test
    void getCharacterByIdOK(){

        // ARRANGE
        Long id = 9L;
        GameCharacter expectedValue = new GameCharacter(9L, "Butcher", 100, 70, 80, 65);
        when(characterRepositoryPort.findById(id)).thenReturn(Optional.of(expectedValue));

        // ACT
        GameCharacter actualValue = characterService.getCharacterById(id);

        // ASSERT
        assertNotNull(actualValue);
        assertEquals(expectedValue, actualValue);
        // Verificamos que el repositorio fue llamado
        verify(characterRepositoryPort.findById(id));

    }

    @Test
    void getCharacterByIdKO(){
        // ARRANGE
        Long id = 9L;
        when(characterRepositoryPort.findById(id)).thenReturn(Optional.empty());

        // ACT + ASSERT
        // Verificamos que lanza la excepción
        assertThrows(GameCharacterNotFoundException.class,
                () -> characterService.getCharacterById(id));
        // Verificamos que el repositorio fue llamado
        verify(characterRepositoryPort).findById(id);
    }

    @Test
    void getAllCharactersOK(){
        //ARRANGE
        // Create a list with a fake character that simulates what would be in the DB
        List<GameCharacter> expectedValue = List.of(new GameCharacter(9L, "Butcher", 100, 70, 80, 65));
        when(characterRepositoryPort.findAll()).thenReturn(expectedValue);

        //ACT
        // Execute the real service method we want to test
        // Internally it will call gameCharacterRepository.findAll which the mock will intercept
        // and return our list instead of hitting the DB
        List<GameCharacter> actualValue = characterService.getAllCharacters();

        //ASSERT
        assertNotNull(actualValue); // Verify the service did not return null
        assertEquals(expectedValue, actualValue);
        verify(characterRepositoryPort.findAll());
    }

    @Test
    void getAllCharactersKO(){
        
        when(characterRepositoryPort.findAll()).thenReturn(List.of());
        assertThrows(EmptyListException.class, () -> characterService.getAllCharacters());
        verify(characterRepositoryPort.findAll());

    }

    @Test
    void createCharacterOK(){
        
        // ARRANGE
        GameCharacter newCharacter = new GameCharacter(9L, "Butcher", 110, 16, 11, 14);
        when(characterRepositoryPort.existsByName("Butcher")).thenReturn(false);
        when(characterRepositoryPort.save(newCharacter)).thenReturn(newCharacter);

        // ACT
        GameCharacter actualValue = characterService.createCharacter(newCharacter);

        //ASSERT
        assertNotNull(actualValue);
        assertEquals(newCharacter, actualValue);
        verify(characterRepositoryPort).existsByName("NewHero");
        verify(characterRepositoryPort).save(newCharacter);

    }

    @Test
    void createCharacterKO(){

        // ARRANGE
        GameCharacter newCharacter = new GameCharacter(9L, "Butcher", 110, 16, 11, 14);
        when(characterRepositoryPort.existsByName("Butcher")).thenReturn(true);        
        
        // ACT + ASSERT
        assertThrows(DuplicateNameException.class, 
            () -> characterService.createCharacter(newCharacter));
        verify(characterRepositoryPort).existsByName("Butcher");

    }

    @Test
    void updateCharacterOK(){
        // ARRANGE
        Long id = 9L;
        GameCharacter existingCharacter = new GameCharacter(9L, "Butcher", 100, 70, 80, 65);
        GameCharacter updatedData = new GameCharacter(null, "Butcher Updated", 110, 75, 85, 70);
        GameCharacter expectedResult = new GameCharacter(9L, "Butcher Updated", 110, 75, 85, 70);
        
        when(characterRepositoryPort.findById(id)).thenReturn(Optional.of(existingCharacter));
        when(characterRepositoryPort.save(existingCharacter)).thenReturn(expectedResult);

        // ACT
        GameCharacter actualValue = characterService.updateCharacter(updatedData, id);

        // ASSERT
        assertNotNull(actualValue);
        assertEquals(expectedResult, actualValue);
        verify(characterRepositoryPort).findById(id);
        verify(characterRepositoryPort).save(existingCharacter);
    }

    @Test
    void updateCharacterKO(){
        // ARRANGE
        Long id = 9L;
        GameCharacter updatedData = new GameCharacter(null, "Butcher Updated", 110, 75, 85, 70);
        when(characterRepositoryPort.findById(id)).thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThrows(GameCharacterNotFoundException.class,
                () -> characterService.updateCharacter(updatedData, id));
        verify(characterRepositoryPort).findById(id);
    }




}
