package com.gamedev.equipment.application.service;

import com.gamedev.equipment.domain.exception.DuplicateNameException;
import com.gamedev.equipment.domain.exception.EmptyListException;
import com.gamedev.equipment.domain.exception.WeaponNotFoundException;
import com.gamedev.equipment.domain.model.Weapon;
import com.gamedev.equipment.domain.port.output.WeaponRepositoryPort;
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
public class WeaponServiceTest {

    @Mock
    private WeaponRepositoryPort weaponRepositoryPort;
    @InjectMocks
    private WeaponService weaponService;

    @Test
    void getWeaponByIdOK(){
        // ARRANGE
        Long id = 9L;
        Weapon expectedWeapon = new Weapon(9L, "Excalibur", 20, 50, 15, true);
        when(weaponRepositoryPort.getById(id)).thenReturn(Optional.of(expectedWeapon));

        // ACT
        Weapon actualWeapon = weaponService.getWeaponById(id);

        // ASSERT
        assertNotNull(actualWeapon);
        assertEquals(expectedWeapon, actualWeapon);
        verify(weaponRepositoryPort).getById(id);
    }

    @Test
    void getWeaponByIdKO(){
        // ARRANGE
        Long id = 9L;
        when(weaponRepositoryPort.getById(id)).thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThrows(WeaponNotFoundException.class,
                () -> weaponService.getWeaponById(id));
    }

    @Test
    void getAllWeaponsOK(){
        // ARRANGE
        List<Weapon> expectedWeapons = List.of(new Weapon(9L, "Excalibur", 20, 50, 15, true));
        when(weaponRepositoryPort.getAll()).thenReturn(expectedWeapons);

        // ACT
        List<Weapon> actualWeapon = weaponService.getAllWeapons();

        // ASSERT
        assertNotNull(actualWeapon);
        assertEquals(expectedWeapons, actualWeapon);
        verify(weaponRepositoryPort).getAll();
    }

    @Test
    void getAllWeaponKO(){
        // ARRANGE
        when(weaponRepositoryPort.getAll()).thenReturn(List.of());

        // ACT & ASSERT
        assertThrows(EmptyListException.class, () -> weaponService.getAllWeapons());
        verify(weaponRepositoryPort).getAll();
    }

    @Test
    void createWeaponOK(){
        // ARRANGE
        Weapon newWeapon = new Weapon(9L, "Excalibur", 20, 50, 15, true);
        when(weaponRepositoryPort.existsByName("Excalibur")).thenReturn(false);
        when(weaponRepositoryPort.save(newWeapon)).thenReturn(newWeapon);

        // ACT
        Weapon actualValue = weaponService.createWeapon(newWeapon);

        // ASSERT
        assertNotNull(actualValue);
        assertEquals(newWeapon, actualValue);
        verify(weaponRepositoryPort).existsByName("Excalibur");
        verify(weaponRepositoryPort).save(newWeapon);
    }

    @Test
    void createWeaponKO(){
        // ARRANGE
        Weapon newWeapon = new Weapon(9L, "Excalibur", 20, 50, 15, true);
        when(weaponRepositoryPort.existsByName("Excalibur")).thenReturn(true);

        // ACT + ASSERT
        assertThrows(DuplicateNameException.class,
                () -> weaponService.createWeapon(newWeapon));
        verify(weaponRepositoryPort).existsByName("Excalibur");
    }

    @Test
    void updateWeaponOK(){
        // ARRANGE
        Long id= 9L;
        Weapon existingWeapon = new Weapon(9L, "Excalibur", 20, 50, 15, true);
        Weapon updatedData = new Weapon(null, "Excalibur", 20, 50, 15, true);
        Weapon expectedResult = new Weapon(9L, "Excalibur", 20, 50, 15, true);

        when(weaponRepositoryPort.getById(id)).thenReturn(Optional.of(existingWeapon));
        when(weaponRepositoryPort.save(existingWeapon)).thenReturn(expectedResult);

        // ACT
        Weapon actualValue = weaponService.updateWeapon(updatedData, id);

        // ASSERT
        assertNotNull(actualValue);
        assertEquals(expectedResult, actualValue);
        verify(weaponRepositoryPort).getById(id);
        verify(weaponRepositoryPort).save(existingWeapon);
    }

    @Test
    void updatedWeaponKO(){
        // ARRANGE
        Long id = 9L;
        Weapon updatedData = new Weapon(null, "Excalibur", 20, 50, 15, true);
        when(weaponRepositoryPort.getById(id)).thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThrows(WeaponNotFoundException.class,
                () -> weaponService.updateWeapon(updatedData, id));
        verify(weaponRepositoryPort).getById(id);
    }


}
