package com.megapet.backendmegapet.adopter.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAdopterResource {
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
}
