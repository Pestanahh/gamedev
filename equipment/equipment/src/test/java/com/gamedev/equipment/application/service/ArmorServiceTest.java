package com.gamedev.equipment.application.service;

import com.gamedev.equipment.domain.exception.ArmorNotFoundException;
import com.gamedev.equipment.domain.exception.DuplicateNameException;
import com.gamedev.equipment.domain.exception.EmptyListException;
import com.gamedev.equipment.domain.model.Armor;
import com.gamedev.equipment.domain.port.output.ArmorRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArmorServiceTest {

    @Mock
    private ArmorRepositoryPort armorRepositoryPort;
    @InjectMocks
    private ArmorService armorService;

    @Test
    void getArmorByIdOK(){
        // ARRANGE
        Long id = 9L;
        Armor expectedArmor = new Armor(9L, "Banished Knight Set", 40, 30);
        when(armorRepositoryPort.findById(id)).thenReturn(Optional.of(expectedArmor));

        // ACT
        Armor actualArmor = armorService.getArmorById(id);

        // ASSERT
        assertNotNull(actualArmor);
        assertEquals(expectedArmor, actualArmor);
        verify(armorRepositoryPort).findById(id);
    }

    @Test
    void getArmorByIdKO(){
        // ARRANGE
        Long id = 9L;
        when(armorRepositoryPort.findById(id)).thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThrows(ArmorNotFoundException.class,
                () -> armorService.getArmorById(id));
    }

    @Test
    void getAllArmorsOK(){
        // ARRANGE
        List<Armor> expectedArmors = List.of(new Armor(9L, "Banished Knight Set", 40, 30));
        when(armorRepositoryPort.findAll()).thenReturn(expectedArmors);

        // ACT
        List<Armor> actualArmor = armorService.getAllArmors();

        // ASSERT
        assertNotNull(actualArmor);
        assertEquals(expectedArmors, actualArmor);
        verify(armorRepositoryPort).findAll();
    }

    @Test
    void getAllArmorKO(){
        // ARRANGE
        when(armorRepositoryPort.findAll()).thenReturn(List.of());

        // ACT & ASSERT
        assertThrows(EmptyListException.class, () -> armorService.getAllArmors());
        verify(armorRepositoryPort).findAll();
    }

    @Test
    void createArmorOK(){
        // ARRANGE
        Armor newArmor = new Armor(9L, "Banished Knight Set", 40, 30);
        when(armorRepositoryPort.existsByName("Banished Knight Set")).thenReturn(false);
        when(armorRepositoryPort.save(newArmor)).thenReturn(newArmor);

        // ACT
        Armor actualValue = armorService.createArmor(newArmor);

        // ASSERT
        assertNotNull(actualValue);
        assertEquals(newArmor, actualValue);
        verify(armorRepositoryPort).existsByName("Banished Knight Set");
        verify(armorRepositoryPort).save(newArmor);
    }

    @Test
    void createArmorKO(){
        // ARRANGE
        Armor newArmor = new Armor(9L, "Banished Knight Set", 40, 30);
        when(armorRepositoryPort.existsByName("Banished Knight Set")).thenReturn(true);

        // ACT + ASSERT
        assertThrows(DuplicateNameException.class,
                () -> armorService.createArmor(newArmor));
        verify(armorRepositoryPort).existsByName("Banished Knight Set");
    }

    @Test
    void updateArmorOK(){
        // ARRANGE
        Long id= 9L;
        Armor existingArmor = new Armor(9L, "Banished Knight Set", 40, 30);
        Armor updatedData = new Armor(null, "Banished Knight Set", 40, 30);
        Armor expectedResult = new Armor(9L, "Banished Knight Set", 40, 30);

        when(armorRepositoryPort.findById(id)).thenReturn(Optional.of(existingArmor));
        when(armorRepositoryPort.save(existingArmor)).thenReturn(expectedResult);

        // ACT
        Armor actualValue = armorService.updateArmor(updatedData, id);

        // ASSERT
        assertNotNull(actualValue);
        assertEquals(expectedResult, actualValue);
        verify(armorRepositoryPort).findById(id);
        verify(armorRepositoryPort).save(existingArmor);
    }

    @Test
    void updatedArmorKO(){
        // ARRANGE
        Long id = 9L;
        Armor updatedData = new Armor(null, "Banished Knight Set", 40, 30);
        when(armorRepositoryPort.findById(id)).thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThrows(ArmorNotFoundException.class,
                () -> armorService.updateArmor(updatedData, id));
        verify(armorRepositoryPort).findById(id);
    }


}
