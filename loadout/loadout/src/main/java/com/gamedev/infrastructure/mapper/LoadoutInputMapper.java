package com.gamedev.infrastructure.mapper;

import com.gamedev.domain.model.LoadoutInput;
import com.gamedev.infrastructure.input.rest.dto.LoadoutInputDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoadoutInputMapper {

    LoadoutInput toDomain(LoadoutInputDTO input);
}
