package com.megapet.backendmegapet.shelter.api;

import com.megapet.backendmegapet.shelter.domain.service.ShelterService;
import com.megapet.backendmegapet.shelter.mapping.ShelterMapper;
import com.megapet.backendmegapet.shelter.resource.CreateShelterResource;
import com.megapet.backendmegapet.shelter.resource.ShelterResource;
import com.megapet.backendmegapet.shelter.resource.UpdateShelterResource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/shelters")
public class SheltersController {
    private final ShelterService shelterService;
    private final ShelterMapper mapper;

    public SheltersController(ShelterService shelterService, ShelterMapper mapper) {
        this.shelterService = shelterService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<ShelterResource> getAllShelters(Pageable pageable) {
        return mapper.modelListPage(shelterService.getAll(), pageable);
    }

    @GetMapping("{shelterId}")
    public ShelterResource getShelterById (@PathVariable Long shelterId) {
        return mapper.toResource(shelterService.getById(shelterId));
    }

    @PostMapping("user/{userId}")
    public ShelterResource createShelter (@Valid @RequestBody CreateShelterResource resource, @Valid @PathVariable Long userId) {
        return mapper.toResource(shelterService.create(mapper.toModel(resource), userId));
    }

    @PutMapping("{shelterId}")
    public ShelterResource updateShelter (@PathVariable Long shelterId, @Valid @RequestBody UpdateShelterResource resource) {
        return mapper.toResource(shelterService.update(shelterId, mapper.toModel(resource)));
    }

    @DeleteMapping("{shelterId}")
    public ResponseEntity<?> deleteShelter(@PathVariable Long shelterId) {
        return shelterService.delete(shelterId);
    }
}
