package com.megapet.backendmegapet.shelter.service;

import com.megapet.backendmegapet.shared.exception.ResourceNotFoundException;
import com.megapet.backendmegapet.shared.exception.ResourceValidationException;
import com.megapet.backendmegapet.shelter.domain.model.entity.Shelter;
import com.megapet.backendmegapet.shelter.domain.persistence.ShelterRepository;
import com.megapet.backendmegapet.shelter.domain.service.ShelterService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ShelterServiceImpl implements ShelterService {
    private static final String ENTITY = "Shelter";
    private final ShelterRepository shelterRepository;
    private final Validator validator;

    public ShelterServiceImpl(ShelterRepository shelterRepository, Validator validator) {
        this.shelterRepository = shelterRepository;
        this.validator = validator;
    }

    @Override
    public List<Shelter> getAll() {
        return shelterRepository.findAll();
    }

    @Override
    public Page<Shelter> getAll(Pageable pageable) {
        return shelterRepository.findAll(pageable);
    }

    @Override
    public Shelter getById(Long shelterId) {
        return shelterRepository
                .findById(shelterId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, shelterId));
    }

    @Override
    public Shelter create(Shelter shelter) {
        Set<ConstraintViolation<Shelter>> violations = validator.validate(shelter);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return shelterRepository.save(shelter);
    }

    @Override
    public Shelter update(Long shelterId, Shelter request) {
        Set<ConstraintViolation<Shelter>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return shelterRepository.findById(shelterId).map(shelter ->
                shelterRepository.save(shelter
                        .withDescription(request.getDescription())
                        .withUrl(request.getUrl())
                        .withRank(request.getRank())
                        .withLocation(request.getLocation())
                        .withVetsCertified(request.getVetsCertified())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, shelterId));
    }

    @Override
    public ResponseEntity<?> delete(Long shelterId) {
        return shelterRepository.findById(shelterId).map(shelter -> {
            shelterRepository.delete(shelter);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, shelterId));
    }
}
