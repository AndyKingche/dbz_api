package com.api.dragonball.user.service.impl;

import com.api.dragonball.user.model.UserModel;
import com.api.dragonball.user.repository.UserRepository;
import com.api.dragonball.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a list of all users.
     *
     * This method returns all the users from the user repository.
     * It uses the `findAll()` method of the repository to fetch all user records.
     *
     * @return A list of [UserModel] representing all users in the system.
     */
    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * This method fetches a user by their ID from the repository.
     * If the user is found, it returns the user wrapped in an [Optional].
     * If the user is not found, it throws an exception indicating the user could not be found.
     *
     * @param id The unique identifier of the user to retrieve.
     * @return An [Optional] containing the [UserModel] if found, or an exception if not.
     */
    @Override
    public Optional<UserModel> getUserById(Long id) {
        UserModel userFound = userRepository.findByUserId(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        return Optional.of(userFound);
    }

     /**
     * Creates a new user.
     *
     * This method checks if the user already exists by comparing the user identification and email.
     * If the user exists, it throws an [IllegalArgumentException].
     * If the user does not exist, it saves the new user in the repository and returns the created user.
     *
     * @param user The [UserModel] object containing the new user's details.
     * @return The created [UserModel] object.
     * @throws IllegalArgumentException If the user already exists based on their identification or email.
     */
    @Override
    public UserModel createUser(UserModel user) {
        Optional<UserModel> userFound = userRepository
                .findByUserIdentificationAndUserEmail(user.getUserIdentification(), user.getUserEmail());

        if (userFound.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        return userRepository.save(user);
    }

    /**
     * Updates an existing user.
     *
     * This method first retrieves the user by ID.
     * If the user exists, it updates the user's details (e.g., identification, name, email, etc.)
     * and saves the updated user back to the repository.
     * If the user is not found, it throws an exception.
     *
     * @param id The unique identifier of the user to update.
     * @param user The [UserModel] object containing the updated user details.
     * @return An [Optional] containing the updated [UserModel], or throws an exception if not found.
     */
    @Override
    public Optional<UserModel> updateUser(Long id, UserModel user) {
        return Optional.ofNullable(getUserById(id).map(userFound -> {
            userFound.setUserIdentification(user.getUserIdentification());
            userFound.setUserName(user.getUserName());
            userFound.setUserLastname(user.getUserLastname());
            userFound.setUserEmail(user.getUserEmail());
            userFound.setUserPassword(user.getUserPassword());
            userFound.setUserAddress(user.getUserAddress());
            userFound.setUserStatus(user.getUserStatus());
            return userRepository.save(userFound);
        }).orElseThrow(() -> new EntityNotFoundException("User not found with ID: "+id)));
    }

    /**
     * Deletes a user by their unique ID.
     *
     * This method attempts to delete a user by their ID.
     * If the user exists, it is deleted from the repository.
     * If the user does not exist, an exception is thrown indicating that the user could not be found.
     *
     * @param id The unique identifier of the user to delete.
     * @return `true` if the user was successfully deleted, otherwise an exception is thrown.
     * @throws EntityNotFoundException If no user with the given ID is found.
     */
    @Override
    public boolean deleteUser(Long id) {
        return getUserById(id).map(userFound -> {
            userRepository.delete(userFound);
            return true;
        }).orElseThrow(() -> new EntityNotFoundException("User not found with ID: "+id));
    }

    /**
     * Retrieves a user by their email.
     *
     * This method fetches a user by their email from the repository.
     * If the user is found, it returns the user wrapped in an [Optional].
     * If the user is not found, it throws an exception indicating the user could not be found.
     *
     * @param email The email of the user to retrieve.
     * @return An [Optional] containing the [UserModel] if found, or throws an exception if not found.
     * @throws EntityNotFoundException If no user with the given email is found.
     */
    @Override
    public Optional<UserModel> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByUserEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: "+email)));
    }
}
