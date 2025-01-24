package com.api.dragonball.service.impl;

import com.api.dragonball.model.CharacterModel;
import com.api.dragonball.repository.CharacterRepository;
import com.api.dragonball.service.CharacterService;
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

    @Override
    public List<CharacterModel> getAllCharacters() {
        return characterRepository.findAll();
    }

    @Override
    public Optional<CharacterModel> getCharacter(Long id) {
        CharacterModel characterFound = characterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Character not found: "+id));
        return Optional.of(characterFound);
    }

    @Override
    public CharacterModel addCharacter(CharacterModel character) {
        Optional<CharacterModel> characterFound = characterRepository.findByCharacterName(character.getCharacterName());
        if (characterFound.isPresent()) {
            throw new IllegalArgumentException("Character already exists: "+character.getCharacterName());
        }
        return characterRepository.save(character);
    }

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

    @Override
    public Boolean deleteCharacter(Long id) {
        return getCharacter(id).map(characterFound -> {
            characterRepository.delete(characterFound);
            return true;
        }).orElseThrow(() -> new EntityNotFoundException("Character not found with ID: " + id));
    }
}
