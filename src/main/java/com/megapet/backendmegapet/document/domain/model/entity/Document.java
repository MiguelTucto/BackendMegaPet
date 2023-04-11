package com.megapet.backendmegapet.document.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.megapet.backendmegapet.adopter.domain.model.entity.Adopter;
import com.megapet.backendmegapet.pet.domain.model.entity.Pet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "documents")
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 2000)
    private String detail;

    @NotBlank
    @NotNull
    @Size(max = 200)
    private String dateAdoption;

    @NotBlank
    @NotNull
    @Size(max = 2000)
    private String location;

    @NotBlank
    @NotNull
    @Size(max = 100)
    private String status;

    private int monthlyIncome;

    @NotBlank
    @NotNull
    @Size(max = 2000)
    private String anotherPet;

    @ManyToOne
    @JoinColumn(name = "adopter_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Adopter adopter;

    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Pet pet;
}
