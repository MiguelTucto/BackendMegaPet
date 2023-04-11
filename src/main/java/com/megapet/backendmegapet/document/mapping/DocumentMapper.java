package com.megapet.backendmegapet.document.mapping;

import com.megapet.backendmegapet.document.domain.model.entity.Document;
import com.megapet.backendmegapet.document.resource.CreateDocumentResource;
import com.megapet.backendmegapet.document.resource.DocumentResource;
import com.megapet.backendmegapet.document.resource.UpdateDocumentResource;
import com.megapet.backendmegapet.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class DocumentMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public DocumentResource toResource(Document model) {
        return mapper.map(model, DocumentResource.class);
    }
    public Page<DocumentResource> modelListPage(List<Document> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, DocumentResource.class), pageable, modelList.size());
    }
    public Document toModel(CreateDocumentResource resource) { return mapper.map(resource, Document.class); }
    public Document toModel(UpdateDocumentResource resource) { return mapper.map(resource, Document.class); }
}
