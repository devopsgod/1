package by.vstu.service.dictionary;

import by.vstu.dto.dictionary.document.EducationDocumentTypeDTO;
import by.vstu.exception.BusinessException;
import by.vstu.exception.EntityNotFoundException;
import by.vstu.model.dictionary.competition.EducationDocumentType;
import by.vstu.repository.dictionary.EducationDocumentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationDocumentTypeService {

    private final EducationDocumentTypeRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public Page<EducationDocumentTypeDTO> findByNamePageable(String name, Pageable pageable) {
        return repository.findByInternalFalseAndNameIgnoreCaseContaining(name, pageable)
                .map(entity -> mapper.map(entity, EducationDocumentTypeDTO.class));
    }

    public EducationDocumentTypeDTO create(EducationDocumentTypeDTO typeDIO) {
        checkNameExists(typeDIO.getName());
        EducationDocumentType type = repository.save(mapper.map(typeDIO, EducationDocumentType.class));
        return mapper.map(type, EducationDocumentTypeDTO.class);
    }

    public EducationDocumentTypeDTO update(Long id, EducationDocumentTypeDTO typeDIO) {
        EducationDocumentType type = findByIdNotNull(id);
        type.setName(typeDIO.getName());
        return mapper.map(repository.save(type), EducationDocumentTypeDTO.class);
    }

    public EducationDocumentType findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Education document type with id " + id + " not found"));
    }

    private void checkNameExists(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException("Education document with name=" + name + " exist");
        }
    }
}
