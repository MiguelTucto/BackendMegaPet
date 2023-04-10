package com.megapet.backendmegapet.pet.mapping;

import com.megapet.backendmegapet.pet.domain.model.entity.Pet;
import com.megapet.backendmegapet.pet.resource.CreatePetResource;
import com.megapet.backendmegapet.pet.resource.PetResource;
import com.megapet.backendmegapet.pet.resource.UpdatePetResource;
import com.megapet.backendmegapet.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PetMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public PetResource toResource(Pet model) { return mapper.map(model, PetResource.class); }

    public Page<PetResource> modelListPage(List<Pet> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, PetResource.class), pageable, modelList.size());
    }

    public Pet toModel(CreatePetResource resource) { return mapper.map(resource, Pet.class); }
    public Pet toModel(UpdatePetResource resource) { return mapper.map(resource, Pet.class); }

}
