package com.digitalbooking.apilodgings.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "category")
public class Category {

    // Dev - Env
    /*
    @SequenceGenerator(name = "category_sequence", sequenceName = "category_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    */

    // Prod - Env
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name = "id")
    @NotNull(message = "The 'id' field cannot be null.")
    private Integer id;

    @Column(name = "title", nullable = false, unique = true, length = 200)
    @NotNull(message = "The 'title' field cannot be null.")
    private String title;

    @Column(name = "description", nullable = false, length = 400)
    @NotNull(message = "The 'description' field cannot be null.")
    private String description;

    @Column(name = "image_url", nullable = false, length = 260)
    @NotNull(message = "The 'imageUrl' field cannot be null.")
    private String imageUrl;

    @Column(name = "deleted")
    private boolean deleted;


    public Category(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.deleted = false;
    }


    public Category() {

    }
}
