package com.megapet.backendmegapet.shelter.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ShelterResource {
    private Long id;
    private String url;
    private int rank;
    private String location;
    private String description;
    private int vetsCertified;
    private Long userId;
}
