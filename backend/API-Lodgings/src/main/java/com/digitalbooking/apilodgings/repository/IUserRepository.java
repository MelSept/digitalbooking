package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "IUserRepository")
public interface IUserRepository extends JpaRepository<User, Integer> {
}