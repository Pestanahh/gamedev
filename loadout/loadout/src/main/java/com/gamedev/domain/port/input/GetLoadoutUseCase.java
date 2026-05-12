package com.gamedev.domain.port.input;

import com.gamedev.domain.model.Loadout;

import java.util.List;

public interface GetLoadoutUseCase {
    Loadout getLoadout(Long id);
}
