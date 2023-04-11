package com.megapet.backendmegapet.pet.service;

import com.megapet.backendmegapet.pet.domain.model.entity.Pet;
import com.megapet.backendmegapet.pet.domain.persistence.PetRepository;
import com.megapet.backendmegapet.pet.domain.service.PetService;
import com.megapet.backendmegapet.shared.exception.ResourceNotFoundException;
import com.megapet.backendmegapet.shared.exception.ResourceValidationException;
import com.megapet.backendmegapet.shelter.domain.model.entity.Shelter;
import com.megapet.backendmegapet.shelter.domain.persistence.ShelterRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PetServiceImpl implements PetService {
    private static final String ENTITY = "Pet";
    private final PetRepository petRepository;
    private final ShelterRepository shelterRepository;
    private final Validator validator;

    public PetServiceImpl(PetRepository petRepository, ShelterRepository shelterRepository, Validator validator) {
        this.petRepository = petRepository;
        this.shelterRepository = shelterRepository;
        this.validator = validator;
    }

    @Override
    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    @Override
    public Page<Pet> getAll(Pageable pageable) {
        return petRepository.findAll(pageable);
    }

    @Override
    public Page<Pet> getAllPetsByType(String type, Pageable pageable) {
        return petRepository.findByTypeOfPet(type, pageable);
    }

    @Override
    public Page<Pet> getAllPetsByShelterId(Long shelterId, Pageable pageable) {
        return petRepository.findPetsByShelterId(shelterId, pageable);
    }

    @Override
    public Pet getById(Long petId) {
        return petRepository
                .findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, petId));
    }

    @Override
    public Pet createPetByShelter(Pet pet, Long shelterId) {
        Set<ConstraintViolation<Pet>> violations = validator.validate(pet);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Shelter shelter = shelterRepository.findById(shelterId)
                .orElseThrow(() -> new ResourceNotFoundException("Shelter", shelterId));

        pet.setShelter(shelter);

        return petRepository.save(pet);
    }
    @Override
    public Pet update(Long petId, Pet request) {
        Set<ConstraintViolation<Pet>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return petRepository.findById(petId).map(pet ->
                petRepository.save(pet
                        .withDateAdopted(request.getDateAdopted())
                        .withDatePosted(request.getDatePosted())
                        .withImage(request.getImage())
                        .withHistory(request.getHistory())
                        .withLikes(request.getLikes())
                        .withName(request.getName())
                        .withTypeOfPet(request.getTypeOfPet())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, petId));
    }

    @Override
    public ResponseEntity<?> delete(Long petId) {
        return petRepository.findById(petId).map(pet -> {
            petRepository.delete(pet);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, petId));
    }
}
