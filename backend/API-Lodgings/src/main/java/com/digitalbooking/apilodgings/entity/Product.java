package com.digitalbooking.apilodgings.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "product")
public class Product {

    // Dev - Env
    /*
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    */

    // Prod - Env
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name = "id")
    @NotNull(message = "The 'id' field cannot be null.")
    private Integer id;

    @Column(name = "title", length = 200, nullable = false)
    @NotNull(message = "The 'title' field cannot be null.")
    @NotBlank(message = "The 'title' field cannot be empty.")
    private String title;

    @Column(name = "description", length = 400, nullable = false)
    @NotNull(message = "The 'description' field cannot be null.")
    @NotBlank(message = "The 'description' field cannot be empty.")
    private String description;

    @Column(name = "available")
    @NotNull(message = "The 'available' field cannot be null.")
    @NotBlank(message = "The 'available' field cannot be empty.")
    private boolean available;

    @Column(name = "deleted_flag")
    private boolean deleted;

    // Reference

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    Set<Image> images;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnore
    Set<ProductFeatures> features = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "place_id")
    Place place;


    public Product(Integer id, String title, String description, boolean available, boolean deleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.available = available;
        this.deleted = deleted;
    }

    public Product() {
    }
}
