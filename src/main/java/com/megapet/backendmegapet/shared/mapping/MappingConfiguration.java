package com.megapet.backendmegapet.shared.mapping;

import com.megapet.backendmegapet.adopter.mapping.AdopterMapper;
import com.megapet.backendmegapet.document.mapping.DocumentMapper;
import com.megapet.backendmegapet.pet.mapping.PetMapper;
import com.megapet.backendmegapet.shelter.mapping.ShelterMapper;
import com.megapet.backendmegapet.user.mapping.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("enhancedModelMapperConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() { return new EnhancedModelMapper(); }

    @Bean
    public UserMapper userMapper() { return  new UserMapper(); }

    @Bean
    public ShelterMapper shelterMapper() { return new ShelterMapper(); }

    @Bean
    public AdopterMapper adopterMapper() { return new AdopterMapper(); }

    @Bean
    public DocumentMapper documentMapper() { return new DocumentMapper(); }

    @Bean
    public PetMapper petMapper() { return new PetMapper(); }
}
