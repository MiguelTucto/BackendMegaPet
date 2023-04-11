package com.megapet.backendmegapet.pet.resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class PetResource {
    private Long id;
    private String name;
    private String history;
    private String image;
    private String datePosted;
    private String dateAdopted;
    private int likes;
    private String typeOfPet;
    private Long shelterId;
}
