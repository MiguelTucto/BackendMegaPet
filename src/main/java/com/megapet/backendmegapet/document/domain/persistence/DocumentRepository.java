package com.megapet.backendmegapet.document.domain.persistence;

import com.megapet.backendmegapet.document.domain.model.entity.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Page<Document> findDocumentsByAdopterId(Long adopterId, Pageable pageable);
}
