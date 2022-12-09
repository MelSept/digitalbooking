package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.Role;
import com.digitalbooking.apilodgings.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "IRoleRepository")
public interface IRoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByTitle(ERole title);
}
