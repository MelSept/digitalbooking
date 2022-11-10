package com.digitalbooking.apilodgings.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "image")
public class Image {

    // Dev - Env
    /*
    @SequenceGenerator(name = "image_sequence", sequenceName = "image_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_sequence")
    */

    // Prod - Env
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name = "id")
    @NotNull(message = "The 'id' field cannot be null.")
    private Integer id;

    @Column(name = "title", nullable = false, length = 200)
    @NotNull(message = "The 'title' field cannot be null.")
    private String title;

    @Column(name = "url", nullable = false, length = 260)
    @NotNull(message = "The 'url' field cannot be null.")
    private String url;

    @Column(name = "deleted_flag")
    private boolean deleted;


    // Reference

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    Product product;


    public Image(Integer id, String title, String url, boolean deleted) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.deleted = deleted;
    }

    public Image() {
    }
}
