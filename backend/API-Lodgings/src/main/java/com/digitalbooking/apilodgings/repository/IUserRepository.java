package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "IUserRepository")
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsernameIgnoreCase(String username);
    Optional<User> findByEmail(String username);

    Optional<User> findByUsernameIgnoreCaseOrEmailEquals(String username, String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}