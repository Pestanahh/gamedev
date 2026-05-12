package com.gamedev.equipment.infrastructure.output.persistence;

import com.gamedev.equipment.domain.model.Helmet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HelmetPersistenceMapper {

    HelmetJpa fromDomainToJpa (Helmet helmet);
    Helmet fromJpaToDomain (HelmetJpa jpa);

}
