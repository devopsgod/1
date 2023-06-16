package by.vstu.service.dictionary;

import by.vstu.dto.dictionary.profile.DocumentTypeDTO;
import by.vstu.exception.BusinessException;
import by.vstu.exception.EntityNotFoundException;
import by.vstu.model.dictionary.profile.DocumentType;
import by.vstu.repository.dictionary.DocumentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentTypeService {

    private final DocumentTypeRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public Page<DocumentTypeDTO> findByNamePageable(String name, Pageable pageable) {
        return repository.findByNameIgnoreCaseContaining(name, pageable)
                .map(entity -> mapper.map(entity, DocumentTypeDTO.class));
    }

    public DocumentTypeDTO create(DocumentTypeDTO typeDIO) {
        checkNameExists(typeDIO.getName());
        DocumentType type = repository.save(mapper.map(typeDIO, DocumentType.class));
        return mapper.map(type, DocumentTypeDTO.class);
    }

    public DocumentTypeDTO update(Long id, DocumentTypeDTO typeDIO) {
        DocumentType type = findByIdNotNull(id);
        type.setName(typeDIO.getName());
        return mapper.map(repository.save(type), DocumentTypeDTO.class);
    }

    public DocumentType findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Document type with id " + id + " not found"));
    }

    private void checkNameExists(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException("Document type with name=" + name + " exist");
        }
    }
}
