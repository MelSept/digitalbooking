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
@Table(name = "policies")
public class Policy {

    // Dev - Env
    /*
    @SequenceGenerator(name = "policy_sequence", sequenceName = "policy_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_sequence")
    */

    // Prod - Env
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "norm", length = 400, nullable = false)
    @NotNull(message = "The 'norm' field cannot be null.")
    @NotBlank(message = "The 'norm' field cannot be empty.")
    private String norm;

    @Column(name = "security", length = 400, nullable = false)
    @NotNull(message = "The 'security' field cannot be null.")
    @NotBlank(message = "The 'security' field cannot be empty.")
    private String security;

    @Column(name = "cancellation", length = 400, nullable = false)
    @NotNull(message = "The 'cancellation' field cannot be null.")
    @NotBlank(message = "The 'cancellation' field cannot be empty.")
    private String cancellation;

    @Column(name = "deleted_flag")
    private boolean deleted = Boolean.FALSE;


    // Reference

    @OneToMany(mappedBy = "policy")
    @JsonIgnore
    Set<Place> places;


    public Policy(Integer id, String norm, String security, String cancellation, boolean deleted) {
        this.id = id;
        this.norm = norm;
        this.security = security;
        this.cancellation = cancellation;
        this.deleted = deleted;
    }

    public Policy() {
    }
}
