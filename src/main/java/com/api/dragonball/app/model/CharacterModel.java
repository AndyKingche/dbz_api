package com.api.dragonball.app.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "character")
@NamedQuery(name = "CharacterModel", query = "SELECT c FROM CharacterModel c")
public class CharacterModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Long id;

    @Column(name = "character_name")
    private String characterName;

    @Column(name = "character_age")
    private Integer characterAge;

    @Column(name = "character_desc")
    private String characterDesc;

    @Column(name = "character_image")
    private String characterImage;

    @Column(name = "character_powerlevel")
    private Integer characterPowerLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Integer getCharacterAge() {
        return characterAge;
    }

    public void setCharacterAge(Integer characterAge) {
        this.characterAge = characterAge;
    }

    public String getCharacterDesc() {
        return characterDesc;
    }

    public void setCharacterDesc(String characterDesc) {
        this.characterDesc = characterDesc;
    }

    public String getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(String characterImage) {
        this.characterImage = characterImage;
    }

    public Integer getCharacterPowerLevel() {
        return characterPowerLevel;
    }

    public void setCharacterPowerLevel(Integer characterPowerLevel) {
        this.characterPowerLevel = characterPowerLevel;
    }
}
