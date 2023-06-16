package by.vstu.service.dictionary;

import by.vstu.dto.dictionary.education.LanguageDTO;
import by.vstu.exception.BusinessException;
import by.vstu.exception.EntityNotFoundException;
import by.vstu.model.dictionary.education.Language;
import by.vstu.repository.dictionary.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public Page<LanguageDTO> findByNamePageable(String name, Pageable pageable) {
        return repository.findByNameIgnoreCaseContaining(name, pageable)
                .map(entity -> mapper.map(entity, LanguageDTO.class));
    }

    public LanguageDTO create(LanguageDTO languageDIO) {
        checkNameExists(languageDIO.getName());
        Language language = repository.save(mapper.map(languageDIO, Language.class));
        return mapper.map(language, LanguageDTO.class);
    }

    public LanguageDTO update(Long id, LanguageDTO languageDIO) {
        Language language = findByIdNotNull(id);
        language.setName(languageDIO.getName());
        return mapper.map(repository.save(language), LanguageDTO.class);
    }

    public Language findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Language with id " + id + " not found"));
    }

    private void checkNameExists(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException("Language with name=" + name + " exist");
        }
    }
}
