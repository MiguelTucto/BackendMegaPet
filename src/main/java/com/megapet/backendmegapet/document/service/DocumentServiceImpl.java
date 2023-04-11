package com.megapet.backendmegapet.document.service;

import com.megapet.backendmegapet.adopter.domain.model.entity.Adopter;
import com.megapet.backendmegapet.adopter.domain.persistence.AdopterRepository;
import com.megapet.backendmegapet.document.domain.model.entity.Document;
import com.megapet.backendmegapet.document.domain.persistence.DocumentRepository;
import com.megapet.backendmegapet.document.domain.service.DocumentService;
import com.megapet.backendmegapet.pet.domain.model.entity.Pet;
import com.megapet.backendmegapet.pet.domain.persistence.PetRepository;
import com.megapet.backendmegapet.shared.exception.ResourceNotFoundException;
import com.megapet.backendmegapet.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final static String ENTITY = "Document";
    private final DocumentRepository documentRepository;
    private final AdopterRepository adopterRepository;
    private final PetRepository petRepository;
    private final Validator validator;

    public DocumentServiceImpl(DocumentRepository documentRepository, AdopterRepository adopterRepository, PetRepository petRepository, Validator validator) {
        this.documentRepository = documentRepository;
        this.adopterRepository = adopterRepository;
        this.petRepository = petRepository;
        this.validator = validator;
    }

    @Override
    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    @Override
    public Page<Document> getAll(Pageable pageable) {
        return documentRepository.findAll(pageable);
    }

    @Override
    public Page<Document> getAllDocumentsByAdopterId(Long adopterId, Pageable pageable) {
        return documentRepository.findDocumentsByAdopterId(adopterId, pageable);
    }

    @Override
    public Document getById(Long documentId) {
        return documentRepository
                .findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, documentId));
    }

    @Override
    public Document createByAdopterIdAndPetId(Document document, Long adopterId, Long petId) {
        Set<ConstraintViolation<Document>> violations = validator.validate(document);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Adopter adopter = adopterRepository.findById(adopterId)
                .orElseThrow(() -> new ResourceNotFoundException("Adopter", adopterId));
        document.setAdopter(adopter);

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException("Pet", petId));
        document.setPet(pet);

        return documentRepository.save(document);
    }

    @Override
    public Document update(Long documentId, Document request) {
        Set<ConstraintViolation<Document>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return documentRepository.findById(documentId).map(document ->
                documentRepository.save(document
                        .withLocation(request.getLocation())
                        .withMonthlyIncome(request.getMonthlyIncome())
                        .withStatus(request.getStatus())
                        .withAnotherPet(request.getAnotherPet())
                        .withDateAdoption(request.getDateAdoption())
                        .withDetail(request.getDetail())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, documentId));
    }

    @Override
    public ResponseEntity<?> delete(Long documentId) {
        return documentRepository.findById(documentId).map(document -> {
            documentRepository.delete(document);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, documentId));
    }
}
