package com.megapet.backendmegapet.shelter.mapping;

import com.megapet.backendmegapet.shared.mapping.EnhancedModelMapper;
import com.megapet.backendmegapet.shelter.domain.model.entity.Shelter;
import com.megapet.backendmegapet.shelter.resource.CreateShelterResource;
import com.megapet.backendmegapet.shelter.resource.ShelterResource;
import com.megapet.backendmegapet.shelter.resource.UpdateShelterResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ShelterMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public ShelterResource toResource(Shelter model) { return mapper.map(model, ShelterResource.class); }

    public Page<ShelterResource> modelListPage(List<Shelter> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ShelterResource.class), pageable, modelList.size());
    }

    public Shelter toModel(CreateShelterResource resource) { return mapper.map(resource, Shelter.class); }
    public Shelter toModel(UpdateShelterResource resource) { return mapper.map(resource, Shelter.class); }
}
