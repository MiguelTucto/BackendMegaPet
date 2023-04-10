package com.megapet.backendmegapet.shelter.domain.persistence;

import com.megapet.backendmegapet.shelter.domain.model.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {

}
