package com.api.dragonball.app.controller;

import com.api.dragonball.messages.MessagesDTO;
import com.api.dragonball.app.model.CharacterModel;
import com.api.dragonball.app.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/dbzapp/")
@CrossOrigin
public class CharacterController {
    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }
    /**
     * Endpoint to retrieve the full list of characters.
     *
     * This method handles HTTP GET requests to the "/list" route and returns a list of [CharacterModel] objects.
     * In case of an error while retrieving the characters, an HTTP 500 (INTERNAL_SERVER_ERROR) status code is returned.
     *
     * @return A [ResponseEntity] object with a 200 OK status code if the operation is successful, or a 500 status code in case of an error.
     */
    @GetMapping("list-character")
     public ResponseEntity<List<CharacterModel>> getAllCharacter() {
        try {
            List<CharacterModel> result = characterService.getAllCharacters();

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }
     /**
     * Endpoint to retrieve a character by its ID.
     *
     * This method handles HTTP GET requests to the "/{id}" route, where {id} is the unique identifier of the character.
     * If the character is found, a [CharacterModel] object is returned in the response with an HTTP 200 OK status code.
     * In case of an error (e.g., the character is not found or there is an issue with the query), a [MessagesDTO] object
     * with an error message and an HTTP 500 (INTERNAL_SERVER_ERROR) status code is returned.
     *
     * @param id The unique identifier of the character to retrieve.
     * @return A [ResponseEntity] object containing the character if successful, or an error message in case of failure.
     */
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
     /**
     * Endpoint to create a new character.
     *
     * This method handles HTTP POST requests to the "/create-character" route, where a [CharacterModel] object
     * is received in the request body. If the character is successfully created, a message with the ID of the created character
     * is returned along with an HTTP 201 (CREATED) status code. In case of an error, an error message is returned with an
     * HTTP 500 (INTERNAL_SERVER_ERROR) status code.
     *
     * @param character The [CharacterModel] object containing the information of the character to create.
     * @return A [ResponseEntity] object containing a success or error message depending on the operation result.
     */
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
     /**
     * Endpoint to update an existing character.
     *
     * This method handles HTTP PUT requests to the route "/update-character/{id}", where the character's ID is
     * received as a parameter in the URL, and a [CharacterModel] object is received in the request body. If the character
     * is successfully updated, a message with the updated character's ID and an HTTP status code 200 (OK) is returned.
     * In case of an error, an error message is returned with HTTP status code 400 (BAD_REQUEST) for invalid data,
     * or an HTTP status code 500 (INTERNAL_SERVER_ERROR) for general errors.
     *
     * @param id The ID of the character to be updated.
     * @param character The [CharacterModel] object with the new character data.
     * @return A [ResponseEntity] object containing a success or error message depending on the result of the operation.
     */
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
     /**
     * Endpoint to delete an existing character.
     *
     * This method handles HTTP DELETE requests to the route "/delete-character/{id}", where the character's ID is
     * received as a parameter in the URL. If the character is successfully deleted, a message with the deleted character's
     * ID and an HTTP status code 200 (OK) is returned. If the character does not exist, a message indicating that the
     * character could not be deleted is returned with HTTP status code 400 (BAD_REQUEST). In case of an error, an error
     * message is returned with HTTP status code 400 (BAD_REQUEST) for invalid data, or an HTTP status code 500
     * (INTERNAL_SERVER_ERROR) for general errors.
     *
     * @param id The ID of the character to be deleted.
     * @return A [ResponseEntity] object containing a success or error message depending on the result of the operation.
     */
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
