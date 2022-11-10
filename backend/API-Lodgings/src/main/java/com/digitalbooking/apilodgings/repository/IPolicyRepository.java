package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPolicyRepository extends JpaRepository<Policy, Integer> {
}