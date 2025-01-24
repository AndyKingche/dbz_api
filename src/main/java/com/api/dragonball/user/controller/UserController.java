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

    @GetMapping("list-user")
     public ResponseEntity<List<UserModel>> getAllUsers() {
        try {
            List<UserModel> result = userService.getAllUsers();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }

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
