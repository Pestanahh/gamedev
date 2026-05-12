package com.gamedev.infrastructure.mapper;

import com.gamedev.domain.model.*;
import com.gamedev.infrastructure.output.equipment.ArmorDTO;
import com.gamedev.infrastructure.output.equipment.EquipmentPieceDTO;
import com.gamedev.infrastructure.output.equipment.HelmetDTO;
import com.gamedev.infrastructure.output.equipment.WeaponDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EquipmentMapper {
    Weapon fromWeaponDtoToDomain(WeaponDTO weaponDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "weight", source = "weight")
    @Mapping(target = "defenseBonus", source = "defenseBonus")
    Armor fromArmorDtoToDomain(ArmorDTO armorDTO);

    Helmet fromHelmetDtoToDomain(HelmetDTO helmetDTO);

    // MapStruct no puede deducir cómo rellenar una List<EquipmentPiece> genérica a partir
    // de tres DTOs separados, así que se implementa a mano como método default.
    // Al compilar, MapStruct detecta que ya tiene implementación y no intenta generarla.
    // Para añadir una pieza nueva (ej. Ring):
    //   1. Añade: Ring fromRingDtoToDomain(RingDTO ringDTO) arriba
    //   2. Añade fromRingDtoToDomain(ringDTO) al List.of(...) de abajo
    //   3. getFinalStats() no se toca
    default Equipment toEquipment(List<EquipmentPieceDTO> pieces) {
        List<EquipmentPiece> domainPieces = pieces.stream()
                .map(piece -> {
                    // comprobamos qué tipo concreto es cada DTO
                    // y llamamos al mapper correspondiente
                    if (piece instanceof ArmorDTO armorDTO) {
                        return (EquipmentPiece) fromArmorDtoToDomain(armorDTO);
                    } else if (piece instanceof HelmetDTO helmetDTO) {
                        return (EquipmentPiece) fromHelmetDtoToDomain(helmetDTO);
                    } else if (piece instanceof WeaponDTO weaponDTO) {
                        return (EquipmentPiece) fromWeaponDtoToDomain(weaponDTO);
                    }
                    throw new IllegalArgumentException("Unknown equipment piece: " + piece.getClass());
                })
                .toList();

        return new Equipment(domainPieces);
    }
}
