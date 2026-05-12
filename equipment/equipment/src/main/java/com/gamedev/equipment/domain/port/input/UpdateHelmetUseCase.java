package com.gamedev.equipment.domain.port.input;

import com.gamedev.equipment.domain.model.Helmet;

public interface UpdateHelmetUseCase {

    Helmet updateHelmet (Helmet helmet, Long id);

}
