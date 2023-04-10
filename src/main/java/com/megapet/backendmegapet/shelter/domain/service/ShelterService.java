package com.megapet.backendmegapet.shelter.domain.service;

import com.megapet.backendmegapet.shelter.domain.model.entity.Shelter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShelterService {
    List<Shelter> getAll();
    Page<Shelter> getAll(Pageable pageable);
    Shelter getById(Long shelterId);
    Shelter create(Shelter shelter, Long userId);
    Shelter update(Long shelterId, Shelter request);
    ResponseEntity<?> delete(Long shelterId);
}
