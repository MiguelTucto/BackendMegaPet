package com.megapet.backendmegapet.pet.domain.persistence;

import com.megapet.backendmegapet.pet.domain.model.entity.Pet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Page<Pet> findPetsByAdopterId(Long adopterId, Pageable pageable);
    Page<Pet> findPetsByShelterId(Long shelterId, Pageable pageable);
    Page<Pet> findByTypeOfPet(String type, Pageable pageable);
}
