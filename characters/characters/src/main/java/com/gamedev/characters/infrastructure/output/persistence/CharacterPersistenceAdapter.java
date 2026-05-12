package com.gamedev.characters.infrastructure.output.persistence;

import com.gamedev.characters.domain.port.output.CharacterRepositoryPort;
import com.gamedev.characters.domain.model.GameCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CharacterPersistenceAdapter implements CharacterRepositoryPort {

    private final GameCharacterJpaRepository jpaRepository;
    private final GameCharacterPersistenceMapper gameCharacterPersistenceMapper;

    @Override
    public List<GameCharacter> findAll() {
        List<GameCharacterJpa> jpaList = jpaRepository.findAll();

        return jpaList.stream().map(jpa -> gameCharacterPersistenceMapper.fromJpaToDomain(jpa)).toList();
    }

    @Override
    public Optional<GameCharacter> findById(Long id){

        Optional<GameCharacterJpa> jpa = jpaRepository.findById(id);

        return jpa.map(jpa1 -> gameCharacterPersistenceMapper.fromJpaToDomain(jpa1));
        // O más limpio:
        // return jpa.map(gameCharacterPersistenceMapper::fromJpaToDomain);
    }
    @Override
    public GameCharacter save(GameCharacter gameCharacter) {
        GameCharacterJpa jpa = gameCharacterPersistenceMapper.fromDomainToJpa(gameCharacter);
        GameCharacterJpa ch = jpaRepository.save(jpa);
        return gameCharacterPersistenceMapper.fromJpaToDomain(ch);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<GameCharacter> findByStats(Integer health, Integer defense){
        List<GameCharacterJpa> jpaList = jpaRepository.findByStats(health, defense);

        return jpaList.stream()
                .map(jpa -> gameCharacterPersistenceMapper.fromJpaToDomain(jpa)).toList();
    }

}
