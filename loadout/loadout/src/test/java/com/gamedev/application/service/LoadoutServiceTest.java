package com.gamedev.application.service;

import com.gamedev.domain.model.*;
import com.gamedev.infrastructure.mapper.CharacterMapper;
import com.gamedev.infrastructure.mapper.EquipmentMapper;
import com.gamedev.infrastructure.output.character.CharacterClient;
import com.gamedev.infrastructure.output.character.CharacterDTO;
import com.gamedev.infrastructure.output.equipment.ArmorDTO;
import com.gamedev.infrastructure.output.equipment.EquipmentClient;
import com.gamedev.infrastructure.output.equipment.EquipmentPieceDTO;
import com.gamedev.infrastructure.output.equipment.HelmetDTO;
import com.gamedev.infrastructure.output.equipment.WeaponDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoadoutServiceTest {

    @Mock
    private CharacterClient characterClient;
    @Mock
    private EquipmentClient equipmentClient;
    @Mock
    private CharacterMapper characterMapper;
    @Mock
    private EquipmentMapper equipmentMapper;

    @InjectMocks
    private LoadoutService loadoutService;

    @Test
    void getLoadoutOK() {
        // ARRANGE
        Long id = 9L;

        CharacterDTO characterDTO = new CharacterDTO();
        List<EquipmentPieceDTO> piecesDTO = List.of(
                new ArmorDTO(),
                new HelmetDTO(),
                new WeaponDTO()
        );

        GameCharacter gameCharacter = new GameCharacter(9L, "Tarnished", 100, 20, 30, 40);
        Equipment equipment = new Equipment(List.of(
                (EquipmentPiece) new Armor(1L, "Armor", 5, 10),
                (EquipmentPiece) new Helmet(2L, "Helmet", 1, 4, 0),
                (EquipmentPiece) new Weapon(3L, "Weapon", 2, 7, 1, false)
        ));

        when(characterClient.getCharacterById(id)).thenReturn(characterDTO);
        when(equipmentClient.getEquipmentPieces(id)).thenReturn(piecesDTO);
        when(characterMapper.fromDtoToDomain(characterDTO)).thenReturn(gameCharacter);
        when(equipmentMapper.toEquipment(piecesDTO)).thenReturn(equipment);

        BaseStats expectedBaseStats = new BaseStats(100, 20, 30, 40);
        FinalStats expectedFinalStats = new FinalStats(100, 27, 45, 40, 0, 0);
        Loadout expectedLoadout = new Loadout(9L, "Tarnished", equipment, expectedBaseStats, expectedFinalStats);

        // ACT
        Loadout actualLoadout = loadoutService.getLoadout(id);

        // ASSERT
        assertNotNull(actualLoadout);
        assertEquals(expectedLoadout, actualLoadout);

        verify(characterClient).getCharacterById(id);
        verify(equipmentClient).getEquipmentPieces(id);
        verify(characterMapper).fromDtoToDomain(characterDTO);
        verify(equipmentMapper).toEquipment(piecesDTO);
    }

    @Test
    void postLoadoutOK() {
        // ARRANGE
        Long idCharacter = 9L;
        LoadoutInput request = new LoadoutInput(1L, 2L, 3L);

        CharacterDTO characterDTO = new CharacterDTO();
        List<EquipmentPieceDTO> piecesDTO = List.of(
                new ArmorDTO(),
                new HelmetDTO(),
                new WeaponDTO()
        );

        GameCharacter gameCharacter = new GameCharacter(9L, "Tarnished", 100, 20, 30, 40);
        Equipment equipment = new Equipment(List.of(
                (EquipmentPiece) new Armor(1L, "Armor", 5, 10),
                (EquipmentPiece) new Helmet(2L, "Helmet", 1, 4, 0),
                (EquipmentPiece) new Weapon(3L, "Weapon", 2, 7, 1, false)
        ));

        when(characterClient.getCharacterById(idCharacter)).thenReturn(characterDTO);
        when(equipmentClient.getEquipmentPiecesByIds(request.getIdArmor(), request.getIdHelmet(), request.getIdWeapon()))
                .thenReturn(piecesDTO);
        when(characterMapper.fromDtoToDomain(characterDTO)).thenReturn(gameCharacter);
        when(equipmentMapper.toEquipment(piecesDTO)).thenReturn(equipment);

        BaseStats expectedBaseStats = new BaseStats(100, 20, 30, 40);
        FinalStats expectedFinalStats = new FinalStats(100, 27, 45, 40, 0, 0);
        Loadout expectedLoadout = new Loadout(9L, "Tarnished", equipment, expectedBaseStats, expectedFinalStats);

        // ACT
        Loadout actualLoadout = loadoutService.postLoadout(idCharacter, request);

        // ASSERT
        assertNotNull(actualLoadout);
        assertEquals(expectedLoadout, actualLoadout);

        verify(characterClient).getCharacterById(idCharacter);
        verify(equipmentClient).getEquipmentPiecesByIds(request.getIdArmor(), request.getIdHelmet(), request.getIdWeapon());
        verify(characterMapper).fromDtoToDomain(characterDTO);
        verify(equipmentMapper).toEquipment(piecesDTO);
    }

    @Test
    void updateLoadoutOK() {
        // ARRANGE
        Long idCharacter = 9L;
        LoadoutInput request = new LoadoutInput(1L, 2L, 3L);

        CharacterDTO characterDTO = new CharacterDTO();
        List<EquipmentPieceDTO> piecesDTO = List.of(
                new ArmorDTO(),
                new HelmetDTO(),
                new WeaponDTO()
        );

        GameCharacter gameCharacter = new GameCharacter(9L, "Tarnished", 100, 20, 30, 40);
        Equipment equipment = new Equipment(List.of(
                (EquipmentPiece) new Armor(1L, "Armor", 5, 10),
                (EquipmentPiece) new Helmet(2L, "Helmet", 1, 4, 0),
                (EquipmentPiece) new Weapon(3L, "Weapon", 2, 7, 1, false)
        ));

        when(characterClient.getCharacterById(idCharacter)).thenReturn(characterDTO);
        when(equipmentClient.getEquipmentPiecesByIds(request.getIdArmor(), request.getIdHelmet(), request.getIdWeapon()))
                .thenReturn(piecesDTO);
        when(characterMapper.fromDtoToDomain(characterDTO)).thenReturn(gameCharacter);
        when(equipmentMapper.toEquipment(piecesDTO)).thenReturn(equipment);

        BaseStats expectedBaseStats = new BaseStats(100, 20, 30, 40);
        FinalStats expectedFinalStats = new FinalStats(100, 27, 45, 40, 0, 0);
        Loadout expectedLoadout = new Loadout(9L, "Tarnished", equipment, expectedBaseStats, expectedFinalStats);

        // ACT
        Loadout actualLoadout = loadoutService.updateLoadout(idCharacter, request);

        // ASSERT
        assertNotNull(actualLoadout);
        assertEquals(expectedLoadout, actualLoadout);

        verify(characterClient).getCharacterById(idCharacter);
        verify(equipmentClient).getEquipmentPiecesByIds(request.getIdArmor(), request.getIdHelmet(), request.getIdWeapon());
        verify(characterMapper).fromDtoToDomain(characterDTO);
        verify(equipmentMapper).toEquipment(piecesDTO);
    }
}
