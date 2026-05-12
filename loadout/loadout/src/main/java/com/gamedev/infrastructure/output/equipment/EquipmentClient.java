package com.gamedev.infrastructure.output.equipment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EquipmentClient {

    private final RestTemplate restTemplate;

    //Base URL for the Equipment service, injected from application configuration.
    @Value("${services.equipment.base-url}")
    private String equipmentBaseUrl;


    public ArmorDTO getArmorById(Long id) {
        return restTemplate.getForObject(equipmentBaseUrl + "/armors/{id}", ArmorDTO.class, id);
    }

    public HelmetDTO getHelmetById(Long id) {
        return restTemplate.getForObject(equipmentBaseUrl + "/helmets/{id}", HelmetDTO.class, id);
    }

    public WeaponDTO getWeaponById(Long id) {
        return restTemplate.getForObject(equipmentBaseUrl + "/weapons/{id}", WeaponDTO.class, id);
    }

    public List<EquipmentPieceDTO> getEquipmentPieces(Long id){
        return List.of(
                getArmorById(id),
                getHelmetById(id),
                getWeaponById(id)
        );
    }

    public List<EquipmentPieceDTO> getEquipmentPiecesByIds(Long idArmor, Long idHelmet, Long idWeapon){
        return List.of(
                getArmorById(idArmor),
                getHelmetById(idHelmet),
                getWeaponById(idWeapon)
        );
    }


}
