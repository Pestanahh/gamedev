package com.gamedev.equipment.domain.port.input;

import com.gamedev.equipment.domain.model.Helmet;

import java.util.List;

public interface GetAllHelmetsUseCase {

    List<Helmet> getAllHelmets();

}
