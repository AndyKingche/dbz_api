package com.api.dragonball.user.controller;

import com.api.dragonball.messages.MessagesDTO;
import com.api.dragonball.user.model.UserModel;
import com.api.dragonball.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user/")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * Retrieves a list of all users.
     *
     * This method handles GET requests to the "/list-user" route and returns a list of all users in the system.
     * If the operation is successful, it returns the list with HTTP status 200 (OK).
     * If an error occurs, it returns an HTTP status 500 (INTERNAL_SERVER_ERROR).
     *
     * @return A [ResponseEntity] containing the list of [UserModel] objects and the corresponding HTTP status.
     */
    @GetMapping("list-user")
     public ResponseEntity<List<UserModel>> getAllUsers() {
        try {
            List<UserModel> result = userService.getAllUsers();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }
     /**
     * Retrieves a user by their ID.
     *
     * This method handles GET requests to the "/user/{id}" route, where the user's ID is passed as a path variable.
     * If the user is found, it returns the user data with HTTP status 200 (OK).
     * If an error occurs or the user is not found, it returns an error message with HTTP status 400 (BAD_REQUEST).
     *
     * @param id The unique identifier of the user.
     * @return A [ResponseEntity] containing the user data or an error message with the corresponding HTTP status.
     */
     @GetMapping("user/{id}")
     public ResponseEntity<Object> getUserById(@PathVariable("id") Long id){
         try {
             Optional<UserModel> result = userService.getUserById(id);
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
     * Creates a new user.
     *
     * This method handles POST requests to the "/create-user" route, where a new [UserModel] object is passed in the request body.
     * If the user is successfully created, it returns a success message with HTTP status 201 (CREATED).
     * If there is an error with the input data, it returns an error message with HTTP status 400 (BAD_REQUEST).
     * For any other errors, it returns an error message with HTTP status 500 (INTERNAL_SERVER_ERROR).
     *
     * @param user The [UserModel] object containing the details of the new user.
     * @return A [ResponseEntity] containing a success or error message and the corresponding HTTP status.
     */
     @PostMapping("create-user")
     public ResponseEntity<MessagesDTO> createUser(@RequestBody UserModel user){
        try {
            UserModel createdUser = userService.createUser(user);

            MessagesDTO createMessage = new MessagesDTO();
            createMessage.setMessage("Person created with ID "+ createdUser.getUserId());
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
     * Updates an existing user.
     *
     * This method handles PUT requests to the "/user-edit/{id}" route, where the user's ID is passed as a path variable
     * and the updated user data is passed in the request body.
     * If the user is successfully updated, it returns a success message with HTTP status 200 (OK).
     * If there is an error with the input data, it returns an error message with HTTP status 400 (BAD_REQUEST).
     * For any other errors, it returns an error message with HTTP status 500 (INTERNAL_SERVER_ERROR).
     *
     * @param id The unique identifier of the user to update.
     * @param user The [UserModel] object containing the new details of the user.
     * @return A [ResponseEntity] containing a success or error message and the corresponding HTTP status.
     */
     @PutMapping("user-edit/{id}")
     public ResponseEntity<MessagesDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserModel user) {
        try {
             Optional<UserModel> updatedUser = userService.updateUser(id, user);

             MessagesDTO updateMessage = new MessagesDTO();
             updateMessage.setMessage("Person updated with ID "+ updatedUser.get().getUserId());
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
     * Deletes a user.
     *
     * This method handles DELETE requests to the "/user-delete/{id}" route, where the user's ID is passed as a path variable.
     * If the user is successfully deleted, it returns a success message with HTTP status 200 (OK).
     * For any errors that occur during the deletion process, it returns an error message with HTTP status 500 (INTERNAL_SERVER_ERROR).
     *
     * @param id The unique identifier of the user to delete.
     * @return A [ResponseEntity] containing a success or error message and the corresponding HTTP status.
     */
     @DeleteMapping("user-delete/{id}")
     public ResponseEntity<MessagesDTO> deleteUser(@PathVariable("id") Long id){
        try {
            userService.deleteUser(id);
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
