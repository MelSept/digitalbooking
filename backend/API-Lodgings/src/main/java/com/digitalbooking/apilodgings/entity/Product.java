package com.digitalbooking.apilodgings.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "products")
public class Product {

    // Dev - Env
    /*
    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    */

    // Prod - Env
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name = "id", nullable = false)
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

    @Column(name = "thumbnail")
    @NotNull(message = "The 'thumbnail' field cannot be null.")
    @NotBlank(message = "The 'thumbnail' field cannot be empty.")
    private String thumbnail;

    @Column(name = "deleted_flag")
    private boolean deleted;

    // Reference

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "product_id", nullable = false,
            foreignKey = @ForeignKey(foreignKeyDefinition = "FOREIGN KEY (product_id) REFERENCES product"))
    private Set<Image> images = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    @JoinTable(name = "products_features",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "feature_id")},
            foreignKey = @ForeignKey(foreignKeyDefinition = "FOREIGN KEY (product_id) REFERENCES product"),
            inverseForeignKey = @ForeignKey(foreignKeyDefinition = "FOREIGN KEY (feature_id) REFERENCES feature"))
    Set<Feature> features = new HashSet<>();

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


    public void addFeature(Feature feature) {
        this.features.add(feature);
        feature.getProducts().add(this);
    }

    public void removeFeature(Integer featureId) {
        Feature feature = this.features.stream().
                filter(f -> Objects.equals(f.getId(), featureId)).findFirst().orElse(null);

        if (feature != null) {
            this.features.remove(feature);
            feature.getProducts().remove(this);
        }
    }
}
