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
import java.util.HashSet;
import java.util.Set;

@Hidden

@Setter
@Getter
@Entity
@Table(name = "features")
@SQLDelete(sql = """
        UPDATE features SET deleted_flag = true WHERE id=?;
        """)
@Where(clause = "deleted_flag=false")
public class Feature {

    // Dev - Env
    /*
    @SequenceGenerator(name = "feature_sequence", sequenceName = "feature_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feature_sequence")
    */

    // Prod - Env
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", unique = true, nullable = false, length = 200)
    @NotNull(message = "The 'title' field cannot be null.")
    @NotBlank(message = "The 'title' field cannot be empty.")
    private String title;

    @Column(name = "icon", nullable = false, length = 40)
    @NotNull(message = "The 'icon' field cannot be null.")
    @NotBlank(message = "The 'icon' field cannot be empty.")
    private String icon;

    @Column(name = "deleted_flag")
    private boolean deleted = Boolean.FALSE;

    // Reference

    @ManyToMany(cascade = {CascadeType.PERSIST},
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

}
