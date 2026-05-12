package com.gamedev.infrastructure.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadoutInputDTO {

    Long idArmor;
    Long idHelmet;
    Long idWeapon;
}
