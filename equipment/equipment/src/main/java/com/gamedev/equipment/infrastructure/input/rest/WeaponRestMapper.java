package com.gamedev.equipment.infrastructure.input.rest;

import com.gamedev.equipment.domain.model.Weapon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeaponRestMapper {
    WeaponDto fromDomainToDto (Weapon weapon);
    Weapon fromDtoToDomain (WeaponDto weaponDto);
}
