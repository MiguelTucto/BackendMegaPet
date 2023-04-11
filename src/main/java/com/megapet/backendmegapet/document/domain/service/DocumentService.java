package com.megapet.backendmegapet.document.domain.service;

import com.megapet.backendmegapet.document.domain.model.entity.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DocumentService {
    List<Document> getAll();
    Page<Document> getAll(Pageable pageable);
    Page<Document> getAllDocumentsByAdopterId(Long adopterId, Pageable pageable);
    Document getById(Long documentId);
    Document createByAdopterIdAndPetId(Document document, Long adopterId, Long petId);
    Document update(Long documentId, Document request);
    ResponseEntity<?> delete(Long documentId);
}
