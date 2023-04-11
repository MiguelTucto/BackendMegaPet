package com.megapet.backendmegapet.document.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class CreateDocumentResource {
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
}
