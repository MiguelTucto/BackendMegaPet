package com.megapet.backendmegapet.adopter.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.megapet.backendmegapet.user.domain.model.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class Adopter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 2000)
    private String description;

    @NotBlank
    @NotNull
    @Size(max = 200)
    private String location;

    @NotBlank
    @NotNull
    @Size(max = 100)
    private String status;

    private int monthlyIncome;

    @NotBlank
    @NotNull
    @Size(max = 100)
    private String anotherPet;

    private int rank;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private User user;
}
