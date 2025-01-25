package com.api.dragonball.app.service.impl;

import com.api.dragonball.app.model.CharacterModel;
import com.api.dragonball.app.repository.CharacterRepository;
import com.api.dragonball.app.service.CharacterService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }
    /**
     * Retrieves a list of all characters.
     *
     * @return A list of CharacterModel objects representing all characters in the database.
     */
    @Override
    public List<CharacterModel> getAllCharacters() {
        return characterRepository.findAll();
    }
    /**
     * Retrieves a character by its ID.
     *
     * @param id The unique identifier of the character.
     * @return An Optional object containing the CharacterModel if the character is found,
     *         or empty if no character is found with the given ID.
     */
    @Override
    public Optional<CharacterModel> getCharacter(Long id) {
        CharacterModel characterFound = characterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Character not found: "+id));
        return Optional.of(characterFound);
    }
      /**
     * Creates a new character in the database.
     *
     * @param character The [CharacterModel] object containing the details of the new character.
     * @return The created [CharacterModel] with the character's details, including the generated ID.
     */
    @Override
    public CharacterModel addCharacter(CharacterModel character) {
        Optional<CharacterModel> characterFound = characterRepository.findByCharacterName(character.getCharacterName());
        if (characterFound.isPresent()) {
            throw new IllegalArgumentException("Character already exists: "+character.getCharacterName());
        }
        return characterRepository.save(character);
    }
     /**
     * Updates an existing character in the database.
     *
     * @param id The unique identifier of the character to update.
     * @param character The [CharacterModel] object containing the new details of the character.
     * @return An [Optional] object containing the updated [CharacterModel] if the character is found,
     *         or empty if no character is found with the given ID.
     */
    @Override
    public Optional<CharacterModel> updateCharacter(Long id, CharacterModel character) {
        return Optional.ofNullable(getCharacter(id).map(characterFound -> {
            characterFound.setCharacterName(character.getCharacterName());
            characterFound.setCharacterDesc(character.getCharacterDesc());
            characterFound.setCharacterPowerLevel(character.getCharacterPowerLevel());
            characterFound.setCharacterAge(character.getCharacterAge());
            characterFound.setCharacterImage(character.getCharacterImage());
            return characterRepository.save(characterFound);
        }).orElseThrow(() -> new EntityNotFoundException("Character not found with ID: " + id)));
    }
    /**
     * Deletes a character from the database.
     *
     * @param id The unique identifier of the character to delete.
     * @return `true` if the character was deleted successfully, `false` if no character was found with the given ID.
     */
    @Override
    public Boolean deleteCharacter(Long id) {
        return getCharacter(id).map(characterFound -> {
            characterRepository.delete(characterFound);
            return true;
        }).orElseThrow(() -> new EntityNotFoundException("Character not found with ID: " + id));
    }
}
