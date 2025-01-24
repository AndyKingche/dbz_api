package com.api.dragonball.user.repository;

import com.api.dragonball.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUserEmail(String userEmail);
    Optional<UserModel> findByUserIdentification(String userIdentification);;
    Optional<UserModel> findByUserId(Long userId);
    Optional<UserModel> findByUserIdentificationAndUserEmail(String userIdentification, String userEmail);
}
