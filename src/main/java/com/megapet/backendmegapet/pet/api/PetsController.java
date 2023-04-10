package com.megapet.backendmegapet.pet.api;

import com.megapet.backendmegapet.pet.domain.service.PetService;
import com.megapet.backendmegapet.pet.mapping.PetMapper;
import com.megapet.backendmegapet.pet.resource.CreatePetResource;
import com.megapet.backendmegapet.pet.resource.PetResource;
import com.megapet.backendmegapet.pet.resource.UpdatePetResource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/pets")
public class PetsController {
    private final PetService petService;
    private final PetMapper mapper;

    public PetsController(PetService petService, PetMapper mapper) {
        this.petService = petService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<PetResource> getAllPets(Pageable pageable) {
        return mapper.modelListPage(petService.getAll(), pageable);
    }

    @GetMapping({"shelter/{shelterId}"})
    public Page<PetResource> getAllPetsByShelterId(@PathVariable Long shelterId, Pageable pageable) {
        return petService.getAllPetsByShelterId(shelterId, pageable).map(mapper::toResource);
    }

    @GetMapping({"adopter/{adopterId}"})
    public Page<PetResource> getAllPetsByAdopterId(@PathVariable Long adopterId, Pageable pageable) {
        return petService.getAllPetsByAdopterId(adopterId, pageable).map(mapper::toResource);
    }

    @GetMapping("{type}")
    public Page<PetResource> getAllPetsByType(@PathVariable String type, Pageable pageable) {
        return petService.getAllPetsByType(type, pageable).map(mapper::toResource);
    }

    @PostMapping("adopter/{adopterId}")
    public PetResource createPetByAdopter(@Valid @RequestBody CreatePetResource resource, @PathVariable Long adopterId) {
        return mapper.toResource(petService.createPetByAdopter(mapper.toModel(resource), adopterId));
    }

    @PostMapping("shelter/{shelterId}")
    public PetResource createPetByShelter(@Valid @RequestBody CreatePetResource resource, @PathVariable Long shelterId) {
        return mapper.toResource(petService.createPetByShelter(mapper.toModel(resource), shelterId));
    }

    @PutMapping("{petId}")
    public PetResource updatePet(@PathVariable Long petId, @Valid @RequestBody UpdatePetResource resource) {
        return mapper.toResource(petService.update(petId, mapper.toModel(resource)));
    }
    @GetMapping("{petId}")
    public PetResource getPetById(@PathVariable Long petId) {
        return mapper.toResource(petService.getById(petId));
    }
}
