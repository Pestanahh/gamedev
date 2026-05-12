package com.gamedev.equipment.infrastructure.output.persistence;

import com.gamedev.equipment.domain.model.Weapon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeaponPersistenceMapper {

    WeaponJpa fromDomainToJpa (Weapon weapon);
    Weapon fromJpaToDomain (WeaponJpa jpa);

}
