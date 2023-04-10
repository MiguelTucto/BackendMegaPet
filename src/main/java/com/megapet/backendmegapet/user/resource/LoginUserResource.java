package com.megapet.backendmegapet.user.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserResource {
    private String email;
    private String password;
}
