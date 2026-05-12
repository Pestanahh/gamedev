package com.gamedev.equipment.domain.port.input;

import com.gamedev.equipment.domain.model.Helmet;

public interface GetHelmetByIdUseCase {

    Helmet getHelmetById (Long id);

}
