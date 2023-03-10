package com.digitalbooking.apilodgings.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Hidden

@Setter
@Getter
@Entity
@Table(name = "categories")
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

    @Column(name = "deleted_flag")
    private boolean deleted = Boolean.FALSE;

    // Reference

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    Set<Place> places;


    public Category(String title, String description, String imageUrl, boolean deleted) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.deleted = deleted;
    }


    public Category() {

    }


    public static Category Normalize(Category category) {

        Category categoryNormalize = new Category();
        categoryNormalize.setId(category.getId());
        categoryNormalize.setTitle(category.getTitle().trim().toLowerCase());
        categoryNormalize.setDescription(category.getDescription().trim().toLowerCase());
        categoryNormalize.setImageUrl(category.getImageUrl().trim().toLowerCase());
        categoryNormalize.setDeleted(category.isDeleted());
        return categoryNormalize;
    }
}
