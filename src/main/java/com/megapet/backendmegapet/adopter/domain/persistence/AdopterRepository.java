package com.megapet.backendmegapet.adopter.domain.persistence;

import com.megapet.backendmegapet.adopter.domain.model.entity.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdopterRepository extends JpaRepository<Adopter, Long> {
}
