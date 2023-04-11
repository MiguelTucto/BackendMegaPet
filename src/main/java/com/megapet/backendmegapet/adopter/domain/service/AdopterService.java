package com.megapet.backendmegapet.adopter.domain.service;

import com.megapet.backendmegapet.adopter.domain.model.entity.Adopter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdopterService {
    List<Adopter> getAll();
    Page<Adopter> getAll(Pageable pageable);
    Adopter getAdopterByUserId(Long userId);
    Adopter getById(Long adopterId);
    Adopter create(Adopter adopter, Long userId);
    Adopter update(Long adopterId, Adopter request);
    ResponseEntity<?> delete(Long adopterId);
}
