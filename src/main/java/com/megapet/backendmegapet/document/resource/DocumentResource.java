package com.megapet.backendmegapet.document.resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class DocumentResource {
    private Long id;
    private String detail;
    private String dateAdoption;
    private String location;
    private String status;
    private int monthlyIncome;
    private String anotherPet;
    private Long adopterId;
    private Long petId;
}
