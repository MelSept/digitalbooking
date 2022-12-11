package com.digitalbooking.apilodgings.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Hidden

@Setter
@Getter
@Entity
@Table(name = "cities")
public class City {

    // Dev - Env
    /*
    @SequenceGenerator(name = "city_sequence", sequenceName = "city_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_sequence")
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

    @Column(name = "deleted_flag")
    private boolean deleted = Boolean.FALSE;

    // Reference
    @OneToMany(mappedBy = "city")
    @JsonIgnore
    Set<Place> places;


    public City(Integer id, String title, boolean deleted) {
        this.id = id;
        this.title = title;
        this.deleted = deleted;
    }

    public City() {
    }

}
