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

            MessagesDTO createMessage = new MessagesDTO();
            createMessage.setMessage("Person created with ID "+ createdCharacter.getId());
            createMessage.setMethod("POST");
            createMessage.setStatus(true);

            return new ResponseEntity<>(createMessage, HttpStatus.CREATED);
        } catch(IllegalArgumentException e){

            MessagesDTO badMessage = new MessagesDTO();
            badMessage.setMessage("Invalid input: " + e.getMessage());
            badMessage.setMethod("POST");
            badMessage.setStatus(false);
            return new ResponseEntity<>(badMessage, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            MessagesDTO errorMessage = new MessagesDTO();
            errorMessage.setMessage("Error" + e.getMessage());
            errorMessage.setMethod("POST");
            errorMessage.setStatus(false);

            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

     }

     @PutMapping("character-edit/{id}")
     public ResponseEntity<MessagesDTO> updateCharacter(@PathVariable("id") Long id, @RequestBody CharacterModel character) {
        try {
            Optional<CharacterModel> updatedCharacter = characterService.updateCharacter(id, character);

            MessagesDTO updateMessage = new MessagesDTO();
             updateMessage.setMessage("Person updated with ID "+ updatedCharacter.get().getId());
             updateMessage.setMethod("PUT");
             updateMessage.setStatus(true);

             return new ResponseEntity<>(updateMessage, HttpStatus.OK);
         } catch(IllegalArgumentException e){

             MessagesDTO badMessage = new MessagesDTO();
             badMessage.setMessage("Invalid input: " + e.getMessage());
             badMessage.setMethod("PUT");
             badMessage.setStatus(false);

             return new ResponseEntity<>(badMessage, HttpStatus.INTERNAL_SERVER_ERROR);
         }
         catch (Exception e) {

             MessagesDTO errorMessage = new MessagesDTO();
             errorMessage.setMessage("Error: " + e.getMessage());
             errorMessage.setMethod("PUT");
             errorMessage.setStatus(false);

             return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

     @DeleteMapping("character-delete/{id}")
     public ResponseEntity<MessagesDTO> deleteCharacter(@PathVariable("id") Long id){
        try {
            characterService.deleteCharacter(id);

            MessagesDTO deleteMessage = new MessagesDTO();
            deleteMessage.setMessage("Person delete with ID "+ id);
            deleteMessage.setMethod("DELETE");
            deleteMessage.setStatus(true);

            return new ResponseEntity<>(deleteMessage, HttpStatus.OK);
        } catch (Exception e) {

            MessagesDTO errorMessage = new MessagesDTO();
            errorMessage.setMessage("Error : " + e.getMessage());
            errorMessage.setMethod("PUT");
            errorMessage.setStatus(false);

            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

     }
}
