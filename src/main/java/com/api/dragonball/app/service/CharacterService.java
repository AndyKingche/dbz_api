package com.api.dragonball.app.service;

import com.api.dragonball.app.model.CharacterModel;

import java.util.List;
import java.util.Optional;

public interface CharacterService {
    /**
     * Retrieves a list of all characters.
     *
     * @return A list of [CharacterModel] objects representing all characters in the database.
     */

    List<CharacterModel> getAllCharacters();
    /**
     * Retrieves a character by its ID.
     *
     * @param id The unique identifier of the character.
     * @return An [Optional] object containing the [CharacterModel] if the character is found,
     *         or empty if no character is found with the given ID.
     */
    Optional<CharacterModel> getCharacter(Long id);
    /**
     * Creates a new character in the database.
     *
     * @param character The [CharacterModel] object containing the details of the new character.
     * @return The created [CharacterModel] with the character's details, including the generated ID.
     */
    CharacterModel addCharacter(CharacterModel character);
    /**
     * Updates an existing character in the database.
     *
     * @param id The unique identifier of the character to update.
     * @param character The [CharacterModel] object containing the new details of the character.
     * @return An [Optional] object containing the updated [CharacterModel] if the character is found,
     *         or empty if no character is found with the given ID.
     */
    Optional<CharacterModel> updateCharacter(Long id, CharacterModel character);
    /**
     * Deletes a character from the database.
     *
     * @param id The unique identifier of the character to delete.
     * @return `true` if the character was deleted successfully, `false` if no character was found with the given ID.
     */
    Boolean deleteCharacter(Long id);
}
