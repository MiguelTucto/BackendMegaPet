package com.megapet.backendmegapet.document.api;

import com.megapet.backendmegapet.document.domain.service.DocumentService;
import com.megapet.backendmegapet.document.mapping.DocumentMapper;
import com.megapet.backendmegapet.document.resource.CreateDocumentResource;
import com.megapet.backendmegapet.document.resource.DocumentResource;
import com.megapet.backendmegapet.document.resource.UpdateDocumentResource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/documents")
public class DocumentsController {
    private final DocumentService documentService;
    private final DocumentMapper mapper;

    public DocumentsController(DocumentService documentService, DocumentMapper mapper) {
        this.documentService = documentService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<DocumentResource> getAllDocuments(Pageable pageable) {
        return mapper.modelListPage(documentService.getAll(), pageable);
    }

    @GetMapping("adopter/{adopterId}")
    public Page<DocumentResource> getAllDocumentsByAdopterId(@PathVariable Long adopterId, Pageable pageable) {
        return documentService.getAllDocumentsByAdopterId(adopterId, pageable).map(mapper::toResource);
    }

    @PostMapping("adopter/{adopterId}/pet/{petId}")
    public DocumentResource createByAdopterIdAndPetId(@PathVariable Long adopterId, @PathVariable Long petId, @Valid @RequestBody CreateDocumentResource resource) {
        return mapper.toResource(documentService.createByAdopterIdAndPetId(mapper.toModel(resource), adopterId, petId));
    }

    @PutMapping("{documentId}")
    public DocumentResource updateDocument(@PathVariable Long documentId, @Valid @RequestBody UpdateDocumentResource resource) {
        return mapper.toResource(documentService.update(documentId, mapper.toModel(resource)));
    }
    @GetMapping("{documentId}")
    public DocumentResource getDocumentById(@PathVariable Long documentId) {
        return mapper.toResource(documentService.getById(documentId));
    }
    @DeleteMapping("{documentId}")
    public ResponseEntity<?> deleteDocument (@PathVariable Long documentId) {
        return documentService.delete(documentId);
    }
}
