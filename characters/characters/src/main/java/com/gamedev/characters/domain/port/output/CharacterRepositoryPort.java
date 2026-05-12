package com.gamedev.characters.domain.port.output;

import com.gamedev.characters.domain.model.GameCharacter;

import java.util.List;
import java.util.Optional;

public interface CharacterRepositoryPort {

    List<GameCharacter> findAll();
    Optional<GameCharacter> findById(Long id);
    boolean existsByName(String name);
    GameCharacter save(GameCharacter gameCharacter);
    boolean existsById(Long id);
    void deleteById(Long id);
    List<GameCharacter> findByStats(Integer health, Integer defense);

}
