package com.api.dragonball.user.service;

import com.api.dragonball.user.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
     /**
     * Retrieves a list of all users.
     *
     * This method is used to get all the users from the system.
     * It returns a list of [UserModel] objects.
     *
     * @return A list of [UserModel] objects representing all users.
     */
    List<UserModel> getAllUsers();

    /**
     * Retrieves a user by their ID.
     *
     * This method is used to fetch a user by their unique identifier (ID).
     * If the user exists, it returns the user details wrapped in an [Optional].
     * If the user is not found, it returns an empty [Optional].
     *
     * @param id The unique identifier of the user to retrieve.
     * @return An [Optional] containing the [UserModel] if found, or empty if not.
     */
    Optional<UserModel> getUserById(Long id);

    /**
     * Creates a new user.
     *
     * This method is used to create a new user in the system.
     * The [UserModel] object containing the user details is passed as an argument.
     * It returns the created [UserModel] object, including the generated ID.
     *
     * @param user The [UserModel] containing the details of the new user.
     * @return The created [UserModel] object.
     */
    UserModel createUser(UserModel user);

    /**
     * Updates an existing user.
     *
     * This method is used to update an existing user's details based on their ID.
     * It returns an [Optional] containing the updated [UserModel] if the user is found and updated.
     * If the user is not found, it returns an empty [Optional].
     *
     * @param id The unique identifier of the user to update.
     * @param user The [UserModel] containing the updated user details.
     * @return An [Optional] containing the updated [UserModel] if found, or empty if not.
     */
    Optional<UserModel> updateUser(Long id, UserModel user);

    /**
     * Deletes a user.
     *
     * This method is used to delete a user from the system based on their ID.
     * It returns `true` if the user was successfully deleted and `false` if no user was found with the given ID.
     *
     * @param id The unique identifier of the user to delete.
     * @return `true` if the user was deleted, `false` if the user does not exist.
     */
    boolean deleteUser(Long id);

    /**
     * Retrieves a user by their email.
     *
     * This method is used to fetch a user based on their email address.
     * If the user exists, it returns the user details wrapped in an [Optional].
     * If the user is not found, it returns an empty [Optional].
     *
     * @param email The email address of the user to retrieve.
     * @return An [Optional] containing the [UserModel] if found, or empty if not.
     */
    Optional<UserModel> getUserByEmail(String email);

}
