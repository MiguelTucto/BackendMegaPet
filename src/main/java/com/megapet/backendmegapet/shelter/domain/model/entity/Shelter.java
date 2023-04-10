package com.megapet.backendmegapet.shelter.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.megapet.backendmegapet.user.domain.model.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shelters")
public class Shelter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private User user;
}
