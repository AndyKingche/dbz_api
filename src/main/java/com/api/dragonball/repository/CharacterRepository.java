package com.api.dragonball.repository;


import com.api.dragonball.model.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterModel, Long> {
    Optional<CharacterModel> findByCharacterName(String characterName);
}
