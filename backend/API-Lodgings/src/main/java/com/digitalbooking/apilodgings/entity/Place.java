package com.digitalbooking.apilodgings.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

@Hidden

@Setter
@Getter
@Entity
@Table(name = "places")
@SQLDelete(sql = """
UPDATE products SET deleted_flag = true WHERE id=?;
""")
@Where(clause = "deleted_flag=false")
public class Place {

    // Dev - Env
    /*
    @SequenceGenerator(name = "place_sequence", sequenceName = "place_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_sequence")
    */

    // Prod - Env
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = 200, nullable = false)
    @NotNull(message = "The 'title' field cannot be null.")
    @NotBlank(message = "The 'title' field cannot be empty.")
    private String title;

    @Column(name = "description", length = 400, nullable = false)
    @NotNull(message = "The 'description' field cannot be null.")
    @NotBlank(message = "The 'description' field cannot be empty.")
    private String description;

    @Column(name = "latitude", nullable = false)
    @NotNull(message = "The 'latitude' field cannot be null.")
    private float latitude = 0;

    @Column(name = "longitude", nullable = false)
    @NotNull(message = "The 'length' field cannot be null.")
    private float longitude = 0;

    @Column(name = "address", length = 260, nullable = false)
    @NotNull(message = "The 'address' field cannot be null.")
    @NotBlank(message = "The 'address' field cannot be empty.")
    private String address;

    @Column(name = "deleted_flag")
    private boolean deleted;

    // Reference

    @OneToMany(mappedBy = "place")
    @JsonIgnore
    Set<Product> products = new LinkedHashSet<>();

    @ManyToOne()
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToOne()
    @JoinColumn(name = "city_id")
    City city;

    @ManyToOne()
    @JoinColumn(name = "policy_id")
    Policy policy;


    public Place(Integer id, String title, String description, float latitude, float longitude, boolean deleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.deleted = deleted;
    }

    public Place() {

    }
}
