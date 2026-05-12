package com.gamedev.infrastructure.mapper;

import com.gamedev.domain.model.*;
import com.gamedev.infrastructure.input.rest.dto.LoadoutResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoadoutResponseMapper {

    @Mapping(source = "characterName", target = "name")
    LoadoutResponseDTO toResponse(Loadout loadout);

    LoadoutResponseDTO.BaseStatsDto fromBaseStatsDomainToDTO(BaseStats baseStats);

    LoadoutResponseDTO.FinalStatsDto fromFinalStatsDomainToDTO(FinalStats finalStats);

    LoadoutResponseDTO.EquipmentDto.WeaponDto fromWeaponDomainToDTO(Weapon weapon);

    LoadoutResponseDTO.EquipmentDto.ArmorDto fromArmorDomainToDTO(Armor armor);

    LoadoutResponseDTO.EquipmentDto.HelmetDto fromHelmetDomainToDTO(Helmet helmet);

    // MapStruct no puede deducir cómo rellenar un EquipmentDTO con tres campos separados
    // a partir de un Equipment que tiene List<EquipmentPiece>, así que se implementa
    // a mano como método default.
    // Al compilar, MapStruct detecta que ya tiene implementación y no intenta generarla.
    // Para añadir una pieza nueva (ej. Ring):
    //   1. Añade: LoadoutResponse.EquipmentDTO.RingDTO fromRingDomainToDTO(Ring ring) arriba
    //   2. Añade el instanceof Ring r en el for de abajo
    default LoadoutResponseDTO.EquipmentDto fromEquipmentDomainToDTO(Equipment equipment) {
        if (equipment == null || equipment.getPieces() == null) return null;

        LoadoutResponseDTO.EquipmentDto dto = new LoadoutResponseDTO.EquipmentDto();

        for (EquipmentPiece piece : equipment.getPieces()) {
            if (piece instanceof Weapon w) {
                dto.setWeapon(fromWeaponDomainToDTO(w));
            } else if (piece instanceof Armor a) {
                dto.setArmor(fromArmorDomainToDTO(a));
            } else if (piece instanceof Helmet h) {
                dto.setHelmet(fromHelmetDomainToDTO(h));
            }
        }
        return dto;
    }
}
