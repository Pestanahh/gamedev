package com.gamedev.characters.infrastructure.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameCharacterJpaRepository extends JpaRepository<GameCharacterJpa, Long> {
    boolean existsByName(String name);

    @Query ("SELECT g FROM GameCharacterJpa g WHERE g.health > :health AND g.defense < :defense")
    List<GameCharacterJpa> findByStats(@Param("health") Integer health, @Param("defense") Integer defense);
}
