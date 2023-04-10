package com.megapet.backendmegapet.pet.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.megapet.backendmegapet.adopter.domain.model.entity.Adopter;
import com.megapet.backendmegapet.shelter.domain.model.entity.Shelter;
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
@Table(name = "pets")
public class Pet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 50)
    private String name;

    @NotBlank
    @NotNull
    @Size(max = 2000)
    private String history;

    @NotBlank
    @NotNull
    @Size(max = 300)
    private String image;

    @NotBlank
    @NotNull
    @Size(max = 200)
    private String datePosted;

    @NotBlank
    @NotNull
    @Size(max = 200)
    private String dateAdopted;

    private int likes;

    @NotBlank
    @NotNull
    @Size(max = 200)
    private String typeOfPet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adopter_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Adopter adopter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Shelter shelter;

}
