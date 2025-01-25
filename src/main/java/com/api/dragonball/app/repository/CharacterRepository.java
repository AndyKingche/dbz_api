package com.api.dragonball.app.repository;


import com.api.dragonball.app.model.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterModel, Long> {
   /**
     * Retrieves a character by its name.
     *
     * @param characterName The name of the character to search for.
     * @return An [Optional] object containing the [CharacterModel] if a character with the given name is found,
     *         or empty if no character is found with the specified name.
     */
    Optional<CharacterModel> findByCharacterName(String characterName);
}
