package com.megapet.backendmegapet.adopter.api;

import com.megapet.backendmegapet.adopter.domain.service.AdopterService;
import com.megapet.backendmegapet.adopter.mapping.AdopterMapper;
import com.megapet.backendmegapet.adopter.resource.AdopterResource;
import com.megapet.backendmegapet.adopter.resource.CreateAdopterResource;
import com.megapet.backendmegapet.adopter.resource.UpdateAdopterResource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/adopters")
public class AdoptersController {
    private final AdopterService adopterService;
    private final AdopterMapper mapper;

    public AdoptersController(AdopterService adopterService, AdopterMapper mapper) {
        this.adopterService = adopterService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<AdopterResource> getAllAdopters(Pageable pageable) {
        return mapper.modelListPage(adopterService.getAll(), pageable);
    }

    @GetMapping("{adopterId}")
    public AdopterResource getAdopterById(@PathVariable Long adopterId) {
        return mapper.toResource(adopterService.getById(adopterId));
    }

    @PostMapping("user/{userId}")
    public AdopterResource createAdopter(@Valid @RequestBody CreateAdopterResource resource, @Valid @PathVariable Long userId) {
        return mapper.toResource(adopterService.create(mapper.toModel(resource), userId));
    }

    @PutMapping("{adopterId}")
    public AdopterResource updateAdopter(@PathVariable Long adopterId, @RequestBody UpdateAdopterResource resource) {
        return mapper.toResource(adopterService.update(adopterId, mapper.toModel(resource)));
    }

    @DeleteMapping("{adopterId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long adopterId) {
        return adopterService.delete(adopterId);
    }
}
