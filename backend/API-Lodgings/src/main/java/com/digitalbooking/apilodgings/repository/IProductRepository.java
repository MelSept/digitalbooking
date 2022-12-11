package com.digitalbooking.apilodgings.repository;

import com.digitalbooking.apilodgings.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where p.id = ?1 and p.deleted = false")
    Optional<Product> findBy_Id(Integer id);

    @Query("select p from Product p where upper(p.title) = upper(?1) and p.deleted = false")
    Optional<Product> findBy_Title(String title);

    @Query("select p from Product p where p.deleted = false")
    List<Product> findAllProducts();

    List<Product> findAllByPlace_Category_TitleIgnoreCaseAndDeletedIsFalse(String categoryTitle);

    List<Product> findAllByPlace_City_TitleIgnoreCaseAndDeletedIsFalse(String cityTitle);

    /**
     * Find All Products stored in the 'products' table. Filtering by 'City Title' and 'Reservation Date'.
     *
     * @return - List of Product
     */
    @Query("""
            select p from Product p inner join p.reservations r
            where upper(p.place.city.title) = upper(?1) and
            (r.checkIn >= ?2 and r.checkIn <= ?3
            or
            r.checkOut >= ?2 and r.checkOut <= ?3
            or
            r.checkIn <= ?2 and r.checkOut >= ?3)
            and
            p.deleted = false
            """)
    List<Product> findAllBy_CityTitle_And_Reservation_CheckIn_Or_CheckOut_IsBetween(String cityTitle, Date checkInStart, Date checkInEnd, Date checkOutStart, Date checkOutEnd);


    int countByPlace_Category_TitleIgnoreCase(String title);


}