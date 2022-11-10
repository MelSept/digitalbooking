package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface IImageRepository extends JpaRepository<Image, Integer> {

    List<Image> findAllByProduct_Id(Integer productId);

}