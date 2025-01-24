package com.api.dragonball.user.service;

import com.api.dragonball.user.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserModel> getAllUsers();
    Optional<UserModel> getUserById(Long id);
    UserModel createUser(UserModel user);
    Optional<UserModel> updateUser(Long id, UserModel user);
    boolean deleteUser(Long id);
    Optional<UserModel> getUserByEmail(String email);

}
