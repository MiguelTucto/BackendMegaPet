package com.megapet.backendmegapet.adopter.mapping;

import com.megapet.backendmegapet.adopter.domain.model.entity.Adopter;
import com.megapet.backendmegapet.adopter.resource.AdopterResource;
import com.megapet.backendmegapet.adopter.resource.CreateAdopterResource;
import com.megapet.backendmegapet.shared.mapping.EnhancedModelMapper;
import com.megapet.backendmegapet.user.resource.UpdateUserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class AdopterMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public AdopterResource toResource(Adopter model) { return mapper.map(model, AdopterResource.class); }

    public Page<AdopterResource> modelListPage(List<Adopter> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, AdopterResource.class), pageable, modelList.size());
    }

    public Adopter toModel(CreateAdopterResource resource) { return mapper.map(resource, Adopter.class); }
    public Adopter toModel(UpdateUserResource resource) {
        return mapper.map(resource, Adopter.class);
    }
}
