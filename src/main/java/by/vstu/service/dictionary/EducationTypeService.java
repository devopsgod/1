package by.vstu.service.dictionary;

import by.vstu.dto.dictionary.education.EducationTypeDTO;
import by.vstu.exception.BusinessException;
import by.vstu.exception.EntityNotFoundException;
import by.vstu.model.dictionary.education.EducationType;
import by.vstu.repository.dictionary.EducationTypeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationTypeService {

    private final EducationTypeRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public Page<EducationTypeDTO> findByNamePageable(String name, Pageable pageable) {
        return repository.findByNameIgnoreCaseContaining(name, pageable)
                .map(entity -> mapper.map(entity, EducationTypeDTO.class));
    }

    public EducationTypeDTO create(EducationTypeDTO typeDIO) {
        checkNameExists(typeDIO.getName());
        EducationType type = repository.save(mapper.map(typeDIO, EducationType.class));
        return mapper.map(type, EducationTypeDTO.class);
    }

    public EducationTypeDTO update(Long id, EducationTypeDTO typeDIO) {
        EducationType type = findByIdNotNull(id);
        type.setName(typeDIO.getName());
        return mapper.map(repository.save(type), EducationTypeDTO.class);
    }

    public EducationType findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Education type with id " + id + " not found"));
    }

    private void checkNameExists(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException("Education type with name=" + name + " exist");
        }
    }
}
