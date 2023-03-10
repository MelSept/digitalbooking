package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Integer> {

}