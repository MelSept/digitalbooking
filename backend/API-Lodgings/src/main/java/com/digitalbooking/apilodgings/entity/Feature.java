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
@Table(name = "feature")
public class Feature implements Comparable<Feature> {

    // Dev - Env
    /*
    @SequenceGenerator(name = "feature_sequence", sequenceName = "feature_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feature_sequence")
    */

    // Prod - Env
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name = "id")
    @NotNull(message = "The 'id' field cannot be null.")
    private Integer id;

    @Column(name = "title", unique = true, nullable = false, length = 200)
    @NotNull(message = "The 'title' field cannot be null.")
    @NotBlank(message = "The 'title' field cannot be empty.")
    private String title;

    @Column(name = "deleted_flag")
    private boolean deleted;

    // Reference

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY,
    mappedBy = "features")
    @JsonIgnore
    Set<Product> products = new HashSet<>();


    public Feature(Integer id, boolean deleted) {
        this.id = id;
        this.deleted = deleted;
    }

    public Feature() {
    }


    @Override
    public int compareTo(Feature o) {
        int result = 0;
        if (this.id > o.id) {
            result = 1;
        }
        if (this.id < o.id) {
            result = -1;
        }
        return result;
    }
}
