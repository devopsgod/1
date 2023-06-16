package by.vstu.service.dictionary;

import by.vstu.dto.dictionary.education.EducationLevelDTO;
import by.vstu.exception.BusinessException;
import by.vstu.exception.EntityNotFoundException;
import by.vstu.model.dictionary.education.EducationLevel;
import by.vstu.repository.dictionary.EducationLevelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationLevelService {

    private final EducationLevelRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public Page<EducationLevelDTO> findByNamePageable(String name, Pageable pageable) {
        return repository.findByNameIgnoreCaseContaining(name, pageable)
                .map(entity -> mapper.map(entity, EducationLevelDTO.class));
    }

    public EducationLevelDTO create(EducationLevelDTO levelDIO) {
        checkNameExists(levelDIO.getName());
        EducationLevel level = repository.save(mapper.map(levelDIO, EducationLevel.class));
        return mapper.map(level, EducationLevelDTO.class);
    }

    public EducationLevelDTO update(Long id, EducationLevelDTO levelDIO) {
        EducationLevel level = findByIdNotNull(id);
        level.setName(levelDIO.getName());
        return mapper.map(repository.save(level), EducationLevelDTO.class);
    }

    public EducationLevel findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Education level with id " + id + " not found"));
    }

    private void checkNameExists(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException("Education level with name=" + name + " exist");
        }
    }
}
