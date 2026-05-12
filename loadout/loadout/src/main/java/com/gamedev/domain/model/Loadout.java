package com.gamedev.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loadout {

    Long characterId;
    String characterName;
    Equipment equipment;
    BaseStats baseStats;
    FinalStats finalStats;

}
