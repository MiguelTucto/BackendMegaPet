package com.megapet.backendmegapet.pet.domain.service;

import com.megapet.backendmegapet.pet.domain.model.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PetService {
    List<Pet> getAll();
    Page<Pet> getAll(Pageable pageable);
    Page<Pet> getAllPetsByType(String type, Pageable pageable);
    Page<Pet> getAllPetsByShelterId(Long shelterId, Pageable pageable);
    Pet getById(Long petId);
    Pet createPetByShelter(Pet pet, Long shelterId);
    Pet update(Long petId, Pet request);
    ResponseEntity<?> delete(Long petId);
}
