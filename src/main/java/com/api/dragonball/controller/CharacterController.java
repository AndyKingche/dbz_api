package com.api.dragonball.controller;

import com.api.dragonball.messages.MessagesDTO;
import com.api.dragonball.model.CharacterModel;
import com.api.dragonball.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/dbzapp/")
@CrossOrigin
public class CharacterController {
    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("list-character")
     public ResponseEntity<List<CharacterModel>> getAllCharacter() {
        try {
            List<CharacterModel> result = characterService.getAllCharacters();

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }

     @GetMapping("character/{id}")
     public ResponseEntity<Object> getCharaacter(@PathVariable("id") Long id){
         try {
             Optional<CharacterModel> result = characterService.getCharacter(id);
             return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e) {
            MessagesDTO response = new MessagesDTO();
            response.setMethod("GET");
            response.setStatus(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
     }

     @PostMapping("create-character")
     public ResponseEntity<MessagesDTO> createPerson(@RequestBody CharacterModel character){
        try {
           CharacterModel createdCharacter = characterService.addCharacter(character);
            MessagesDTO menssagedto = new MessagesDTO();
            menssagedto.setMessage("Person created with ID "+ createdCharacter.getId());
            menssagedto.setMethod("POST");
            menssagedto.setStatus(true);
            return new ResponseEntity<>(menssagedto, HttpStatus.CREATED);
        } catch(IllegalArgumentException e){
            MessagesDTO badMessagedto = new MessagesDTO();
            badMessagedto.setMessage("Invalid input: " + e.getMessage());
            badMessagedto.setMethod("POST");
            badMessagedto.setStatus(false);
            return new ResponseEntity<>(badMessagedto, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            MessagesDTO badMessagedto = new MessagesDTO();
            badMessagedto.setMessage("Error" + e.getMessage());
            badMessagedto.setMethod("POST");
            badMessagedto.setStatus(false);
            return new ResponseEntity<>(badMessagedto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

     }

     @PutMapping("character-edit/{id}")
     public ResponseEntity<MessagesDTO> updateCharacter(@PathVariable("id") Long id, @RequestBody CharacterModel character) {
        try {
            Optional<CharacterModel> updatedCharacter = characterService.updateCharacter(id, character);
             MessagesDTO menssagedto = new MessagesDTO();
             menssagedto.setMessage("Person updated with ID "+ updatedCharacter.get().getId());
             menssagedto.setMethod("PUT");
             menssagedto.setStatus(true);
             return new ResponseEntity<>(menssagedto, HttpStatus.OK);
         } catch(IllegalArgumentException e){
             MessagesDTO badMessagedto = new MessagesDTO();
             badMessagedto.setMessage("Invalid input: " + e.getMessage());
             badMessagedto.setMethod("PUT");
             badMessagedto.setStatus(false);
             return new ResponseEntity<>(badMessagedto, HttpStatus.INTERNAL_SERVER_ERROR);
         }
         catch (Exception e) {
             MessagesDTO badMessagedto = new MessagesDTO();
             badMessagedto.setMessage("Error: " + e.getMessage());
             badMessagedto.setMethod("PUT");
             badMessagedto.setStatus(false);
             return new ResponseEntity<>(badMessagedto, HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

     @DeleteMapping("character-delete/{id}")
     public ResponseEntity<MessagesDTO> deleteCharacter(@PathVariable("id") Long id){
        try {
            characterService.deleteCharacter(id);
            MessagesDTO menssagedto = new MessagesDTO();
            menssagedto.setMessage("Person delete with ID "+ id);
            menssagedto.setMethod("DELETE");
            menssagedto.setStatus(true);
            return new ResponseEntity<>(menssagedto, HttpStatus.OK);
        } catch (Exception e) {
            MessagesDTO badMessagedto = new MessagesDTO();
            badMessagedto.setMessage("Error : " + e.getMessage());
            badMessagedto.setMethod("PUT");
            badMessagedto.setStatus(false);
            return new ResponseEntity<>(badMessagedto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

     }
}
