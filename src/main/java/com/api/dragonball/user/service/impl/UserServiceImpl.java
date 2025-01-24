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

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        UserModel userFound = userRepository.findByUserId(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        return Optional.of(userFound);
    }

    @Override
    public UserModel createUser(UserModel user) {
        Optional<UserModel> userFound = userRepository
                .findByUserIdentificationAndUserEmail(user.getUserIdentification(), user.getUserEmail());

        if (userFound.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        return userRepository.save(user);
    }

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

    @Override
    public boolean deleteUser(Long id) {
        return getUserById(id).map(userFound -> {
            userRepository.delete(userFound);
            return true;
        }).orElseThrow(() -> new EntityNotFoundException("User not found with ID: "+id));
    }

    @Override
    public Optional<UserModel> getUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByUserEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: "+email)));
    }
}
