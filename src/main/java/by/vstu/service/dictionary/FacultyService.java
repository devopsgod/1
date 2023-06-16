package by.vstu.service.dictionary;

import by.vstu.dto.dictionary.speciality.FacultyDTO;
import by.vstu.exception.BusinessException;
import by.vstu.exception.EntityNotFoundException;
import by.vstu.model.dictionary.competition.Faculty;
import by.vstu.repository.dictionary.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public Page<FacultyDTO> findByNamePageable(String name, Pageable pageable) {
        return repository.findByNameIgnoreCaseContaining(name, pageable)
                .map(entity -> mapper.map(entity, FacultyDTO.class));
    }

    public FacultyDTO create(FacultyDTO facultyDIO) {
        checkNameExists(facultyDIO.getName());
        Faculty faculty = repository.save(mapper.map(facultyDIO, Faculty.class));
        return mapper.map(faculty, FacultyDTO.class);
    }

    public FacultyDTO update(FacultyDTO facultyDIO) {
        checkNameExists(facultyDIO.getName());
        Faculty faculty = repository.save(mapper.map(facultyDIO, Faculty.class));
        return mapper.map(faculty, FacultyDTO.class);
    }

    public Faculty findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Faculty with id " + id + " not found"));
    }

    private void checkNameExists(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException("Faculty with name=" + name + " exist");
        }
    }
}
