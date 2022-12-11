package com.digitalbooking.apilodgings.entity;

import com.digitalbooking.apilodgings.enums.ERole;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Hidden

@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {

    // Dev - Env
    /*
    @SequenceGenerator(name = "role_sequence", sequenceName = "role_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
    */

    // Prod - Env
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name = "id")
    public Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "title", nullable = false, length = 100)
    public ERole title;

    @Column(name = "deleted_flag", nullable = false)
    public boolean deleted = Boolean.FALSE;


    public Role(Integer id, ERole title, boolean deleted) {
        this.id = id;
        this.title = title;
        this.deleted = deleted;
    }

    public Role() {

    }
}
