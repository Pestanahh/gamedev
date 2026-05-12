package com.gamedev.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseStats {

    Integer health;
    Integer attack;
    Integer defense;
    Integer speed;
}
