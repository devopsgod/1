package by.vstu.service.dictionary;

import by.vstu.dto.dictionary.profile.NationalityDTO;
import by.vstu.exception.BusinessException;
import by.vstu.exception.EntityNotFoundException;
import by.vstu.model.dictionary.profile.Nationality;
import by.vstu.repository.dictionary.NationalityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NationalityService {

    private final NationalityRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public Page<NationalityDTO> findByNamePageable(String name, Pageable pageable) {
        return repository.findByNameIgnoreCaseContaining(name, pageable)
                .map(entity -> mapper.map(entity, NationalityDTO.class));
    }

    public NationalityDTO create(NationalityDTO nationalityDIO) {
        checkNameExists(nationalityDIO.getName());
        Nationality nationality = repository.save(mapper.map(nationalityDIO, Nationality.class));
        return mapper.map(nationality, NationalityDTO.class);
    }

    public NationalityDTO update(Long id, NationalityDTO nationalityDIO) {
        Nationality nationality = findByIdNotNull(id);
        nationality.setName(nationalityDIO.getName());
        return mapper.map(repository.save(nationality), NationalityDTO.class);
    }

    public Nationality findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Natiolnality with id " + id + " not found"));
    }

    private void checkNameExists(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException("Nationality with name=" + name + " exist");
        }
    }
}
