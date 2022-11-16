package com.digitalbooking.apilodgings.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "images")
public class Image {

    // Dev - Env
    /*
    @SequenceGenerator(name = "image_sequence", sequenceName = "image_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_sequence")
    */

    // Prod - Env
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name = "id", nullable = false)
    @NotNull(message = "The 'id' field cannot be null.")
    private Integer id;

    @Column(name = "title", unique = true, nullable = false, length = 200)
    @NotNull(message = "The 'title' field cannot be null.")
    private String title;

    @Column(name = "url", nullable = false, length = 260)
    @NotNull(message = "The 'url' field cannot be null.")
    private String url;

    @Column(name = "deleted_flag", nullable = false)
    private boolean deleted;


    // Reference


    public Image(Integer id, String title, String url, boolean deleted) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.deleted = deleted;
    }

    public Image() {
    }
}
