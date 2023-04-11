package com.megapet.backendmegapet.adopter.service;

import com.megapet.backendmegapet.adopter.domain.model.entity.Adopter;
import com.megapet.backendmegapet.adopter.domain.persistence.AdopterRepository;
import com.megapet.backendmegapet.adopter.domain.service.AdopterService;
import com.megapet.backendmegapet.shared.exception.ResourceNotFoundException;
import com.megapet.backendmegapet.shared.exception.ResourceValidationException;
import com.megapet.backendmegapet.user.domain.model.entity.User;
import com.megapet.backendmegapet.user.domain.persistence.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AdopterServiceImpl implements AdopterService {
    private static final String ENTITY = "Adopter";
    private final AdopterRepository adopterRepository;
    private final UserRepository userRepository;
    private final Validator validator;

    public AdopterServiceImpl(AdopterRepository adopterRepository, UserRepository userRepository, Validator validator) {
        this.adopterRepository = adopterRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public List<Adopter> getAll() {
        return adopterRepository.findAll();
    }

    @Override
    public Page<Adopter> getAll(Pageable pageable) {
        return adopterRepository.findAll(pageable);
    }

    @Override
    public Adopter getAdopterByUserId(Long userId) {
        return  adopterRepository.findAdopterByUserId(userId);
    }

    @Override
    public Adopter getById(Long adopterId) {
        return adopterRepository
                .findById(adopterId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, adopterId));
    }

    @Override
    public Adopter create(Adopter adopter, Long userId) {
        Set<ConstraintViolation<Adopter>> violations = validator.validate(adopter);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));

        adopter.setUser(user);

        return adopterRepository.save(adopter);
    }

    @Override
    public Adopter update(Long adopterId, Adopter request) {
        Set<ConstraintViolation<Adopter>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return adopterRepository.findById(adopterId).map(adopter ->
                adopterRepository.save(adopter
                        .withAnotherPet(request.getAnotherPet())
                        .withDescription(request.getDescription())
                        .withRank(request.getRank())
                        .withLocation(request.getLocation())
                        .withMonthlyIncome(request.getMonthlyIncome())
                        .withStatus(adopter.getStatus())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, adopterId));
    }

    @Override
    public ResponseEntity<?> delete(Long adopterId) {
        return adopterRepository.findById(adopterId).map(adopter -> {
            adopterRepository.delete(adopter);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, adopterId));
    }
}
