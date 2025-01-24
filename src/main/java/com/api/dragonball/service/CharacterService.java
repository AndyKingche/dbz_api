package com.api.dragonball.service;

import com.api.dragonball.model.CharacterModel;

import java.util.List;
import java.util.Optional;

public interface CharacterService {
    List<CharacterModel> getAllCharacters();
    Optional<CharacterModel> getCharacter(Long id);
    CharacterModel addCharacter(CharacterModel character);
    Optional<CharacterModel> updateCharacter(Long id, CharacterModel character);
    Boolean deleteCharacter(Long id);
}
