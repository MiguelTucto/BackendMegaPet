package com.megapet.backendmegapet.shelter.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResource {
    @NotNull
    @NotBlank
    @Size(max = 400)
    private String url;

    private int rank;

    @NotNull
    @NotBlank
    @Size(max = 150)
    private String location;

    @NotNull
    @NotBlank
    @Size(max = 2000)
    private String description;

    private int vetsCertified;
}
