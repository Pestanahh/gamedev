package com.gamedev.application.service;

import com.gamedev.domain.model.*;
import com.gamedev.domain.port.input.GetLoadoutUseCase;
import com.gamedev.domain.port.input.PostLoadoutUseCase;
import com.gamedev.domain.port.input.UpdateLoadoutUseCase;
import com.gamedev.infrastructure.mapper.CharacterMapper;
import com.gamedev.infrastructure.mapper.EquipmentMapper;
import com.gamedev.infrastructure.output.character.CharacterClient;
import com.gamedev.infrastructure.output.character.CharacterDTO;
import com.gamedev.infrastructure.output.equipment.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoadoutService implements GetLoadoutUseCase, PostLoadoutUseCase, UpdateLoadoutUseCase {

    // El servicio habla con los clients, y estos con Rest Template
    // por eso inyectamos los clients y sus mappers
    private final CharacterClient characterClient;
    private final EquipmentClient equipmentClient;
    private final CharacterMapper characterMapper;
    private final EquipmentMapper equipmentMapper;

    public Loadout getLoadout(Long id){

        CharacterDTO characterDTO = characterClient.getCharacterById(id);
        List<EquipmentPieceDTO> equipmentPieceDTO = equipmentClient.getEquipmentPieces(id);

        GameCharacter gameCharacter = characterMapper.fromDtoToDomain(characterDTO);
        Equipment equipment = equipmentMapper.toEquipment(equipmentPieceDTO);

        BaseStats baseStats = new BaseStats(
                gameCharacter.getHealth(),
                gameCharacter.getAttack(),
                gameCharacter.getDefense(),
                gameCharacter.getSpeed()
        );

        FinalStats finalStats = getFinalStats(baseStats, equipment);

        return new Loadout(
                gameCharacter.getId(),
                gameCharacter.getName(),
                equipment,
                baseStats,
                finalStats
        );
    }

    public Loadout postLoadout (Long idCharacter, LoadoutInput request){

        CharacterDTO characterDTO = characterClient.getCharacterById(idCharacter);
        List<EquipmentPieceDTO> equipmentPieceDTO = equipmentClient.getEquipmentPiecesByIds(request.getIdArmor(),
                                                                                            request.getIdHelmet(),
                                                                                            request.getIdWeapon());
        GameCharacter gameCharacter = characterMapper.fromDtoToDomain(characterDTO);
        Equipment equipment = equipmentMapper.toEquipment(equipmentPieceDTO);

        BaseStats baseStats = new BaseStats(
                gameCharacter.getHealth(),
                gameCharacter.getAttack(),
                gameCharacter.getDefense(),
                gameCharacter.getSpeed()
        );

        FinalStats finalStats = getFinalStats(baseStats, equipment);

        return new Loadout(
                gameCharacter.getId(),
                gameCharacter.getName(),
                equipment,
                baseStats,
                finalStats
        );
    }

    public Loadout updateLoadout(Long idCharacter, LoadoutInput request){
        CharacterDTO characterDTO = characterClient.getCharacterById(idCharacter);
        List<EquipmentPieceDTO> equipmentPieceDTO = equipmentClient.getEquipmentPiecesByIds(request.getIdArmor(),
                request.getIdHelmet(),
                request.getIdWeapon());
        GameCharacter gameCharacter = characterMapper.fromDtoToDomain(characterDTO);
        Equipment equipment = equipmentMapper.toEquipment(equipmentPieceDTO);

        BaseStats baseStats = new BaseStats(
                gameCharacter.getHealth(),
                gameCharacter.getAttack(),
                gameCharacter.getDefense(),
                gameCharacter.getSpeed()
        );

        FinalStats finalStats = getFinalStats(baseStats, equipment);

        return new Loadout(
                gameCharacter.getId(),
                gameCharacter.getName(),
                equipment,
                baseStats,
                finalStats
        );
    }



    private FinalStats getFinalStats(BaseStats stats, Equipment equipment){

        Integer finalAttack = stats.getAttack()
                + equipment.getPieces().stream()
                .mapToInt(EquipmentPiece::getAttackBonus)
                .sum();

        Integer finalDefense = stats.getDefense()
                + equipment.getPieces().stream()
                .mapToInt(EquipmentPiece::getDefenseBonus)
                .sum();

        return new FinalStats(stats.getHealth(), finalAttack, finalDefense, stats.getSpeed(), 0, 0);
    }




}
