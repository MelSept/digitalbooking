package com.digitalbooking.apilodgings.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

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

    @Column(name = "first_name", nullable = false, length = 80)
    public String firstName;

    @Column(name = "last_name", nullable = false, length = 80)
    public String lastName;

    @Column(name = "username", unique = true, nullable = false, length = 60)
    String username;

    @Column(name = "email", unique = true, nullable = false, length = 120)
    public String email;

    @Column(name = "password", nullable = false, length = 200)
    public String password;

    @Column(name = "city", nullable = false, length = 200)
    public String city;

    @Column(name = "deleted_flag", nullable = false)
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;



    public User(Integer id, String firstName, String lastName, String email, String password, String city, boolean deleted) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.city = city;
        this.deleted = deleted;
    }

    public User() {
    }
}
