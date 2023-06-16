package by.vstu.service.dictionary;

import by.vstu.dto.dictionary.document.SubjectDTO;
import by.vstu.exception.BusinessException;
import by.vstu.exception.EntityNotFoundException;
import by.vstu.model.dictionary.competition.Subject;
import by.vstu.repository.dictionary.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public Page<SubjectDTO> findByNamePageable(String name, Pageable pageable) {
        return repository.findByInternalFalseAndNameIgnoreCaseContaining(name, pageable)
                .map(entity -> mapper.map(entity, SubjectDTO.class));
    }

    public SubjectDTO create(SubjectDTO subjectDIO) {
        checkNameExists(subjectDIO.getName());
        Subject subject = repository.save(mapper.map(subjectDIO, Subject.class));
        return mapper.map(subject, SubjectDTO.class);
    }

    public SubjectDTO update(Long id, SubjectDTO subjectDIO) {
        Subject subject = findByIdNotNull(id);
        subject.setName(subjectDIO.getName());
        return mapper.map(repository.save(subject), SubjectDTO.class);
    }

    public Subject findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject with id " + id + " not found"));
    }

    private void checkNameExists(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException("Subject with name=" + name + " exist");
        }
    }
}
