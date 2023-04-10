package com.megapet.backendmegapet.adopter.resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class AdopterResource {
    private Long id;
    private String description;
    private String location;
    private String status;
    private int monthlyIncome;
    private String anotherPet;
    private int rank;
    private Long userId;
}
