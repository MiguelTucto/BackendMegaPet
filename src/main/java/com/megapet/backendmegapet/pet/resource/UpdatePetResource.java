package com.megapet.backendmegapet.pet.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePetResource {
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
}
