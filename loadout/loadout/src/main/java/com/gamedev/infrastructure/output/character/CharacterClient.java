package com.gamedev.infrastructure.output.character;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor // Lombok generates a constructor with all final fields
public class CharacterClient {

    private final RestTemplate restTemplate;

    //Base URL for the Character service, injected from application configuration.
    @Value("${services.character.base-url}")
    private String characterBaseUrl;


    public CharacterDTO getCharacterById(Long id) {
        return restTemplate.getForObject(characterBaseUrl + "/characters/{id}", CharacterDTO.class, id);
    }


}
