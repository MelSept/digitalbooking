package com.digitalbooking.apilodgings.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Hidden

@Setter
@Getter
@Entity
@Table(name = "images")
@SQLDelete(sql = """
UPDATE images SET deleted_flag = true WHERE id=?;
""")
@Where(clause = "deleted_flag=false")
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
    private Integer id;

    @Column(name = "title", unique = true, nullable = false, length = 200)
    @NotNull(message = "The 'title' field cannot be null.")
    private String title;

    @Column(name = "url", nullable = false, length = 260)
    @NotNull(message = "The 'url' field cannot be null.")
    private String url;

    @Column(name = "deleted_flag", nullable = false)
    private boolean deleted = Boolean.FALSE;



    // Reference
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Product product;


    public Image(Integer id, String title, String url, boolean deleted) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.deleted = deleted;
    }

    public Image() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image )) return false;
        return id != null && id.equals(((Image) o).getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
