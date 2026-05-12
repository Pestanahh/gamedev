package com.gamedev.infrastructure.input.rest.dto;

import lombok.Data;

@Data
public class LoadoutResponseDTO {

    Long characterId;
    String name;
    BaseStatsDto baseStats;
    EquipmentDto equipment;
    FinalStatsDto finalStats;

    @Data
    public static class BaseStatsDto {
        Integer health;
        Integer attack;
        Integer defense;
        Integer speed;
    }

    @Data
    public static class EquipmentDto {
        WeaponDto weapon;
        ArmorDto armor;
        HelmetDto helmet;

        @Data
        public static class WeaponDto {
            private String name;
            private Integer weight;
            private Integer attackBonus;
            private Integer defenseBonus;
            private Boolean isMagical;
        }

        @Data
        public static class ArmorDto {
            private String name;
            private Integer weight;
            private Integer defenseBonus;
        }

        @Data
        public static class HelmetDto {
            private String name;
            private Integer weight;
            private Integer defenseBonus;
            private Integer focusBonus;
        }
    }

    @Data
    public static class FinalStatsDto {
        Integer health;
        Integer attack;
        Integer defense;
        Integer speed;
        Integer focusBonus;
        Integer totalWeight;
    }
}
