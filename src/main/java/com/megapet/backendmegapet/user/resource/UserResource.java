package com.megapet.backendmegapet.user.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    private Long id;
    private String name;
    private String lastName;
    private int phone;
    private String image;
    private String email;
    private String password;
}
