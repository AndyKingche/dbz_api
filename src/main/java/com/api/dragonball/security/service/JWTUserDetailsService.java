package com.api.dragonball.security.service;

import com.api.dragonball.user.model.UserModel;
import com.api.dragonball.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JWTUserDetailsService  implements UserDetailsService {
    private final UserService userService;
    @Autowired
    public JWTUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Optional<UserModel> userFound = userService.getUserByEmail(email);
            if (userFound.isPresent()) {
                return new User(userFound.get().getUserEmail(), userFound.get().getUserPassword()
                        , new
                        ArrayList<>());
            } else {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
        } catch (Exception e) {
            System.out.println("Error genereting token"+e);
            throw new UsernameNotFoundException("Error, can not charger User", e);

        }
    }
}
