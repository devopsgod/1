package by.vstu.service.dictionary;

import by.vstu.dto.dictionary.speciality.SpecialityGroupDTO;
import by.vstu.exception.BusinessException;
import by.vstu.exception.EntityNotFoundException;
import by.vstu.model.dictionary.competition.SpecialityGroup;
import by.vstu.repository.dictionary.SpecialityGroupRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecialityGroupService {

    private final SpecialityGroupRepository repository;

    private final ModelMapper mapper = new ModelMapper();

    public Page<SpecialityGroupDTO> findByNamePageable(String name, Pageable pageable) {
        return repository.findByNameIgnoreCaseContaining(name, pageable)
                .map(entity -> mapper.map(entity, SpecialityGroupDTO.class));
    }

    public SpecialityGroupDTO create(SpecialityGroupDTO specialityGroupDIO) {
        checkNameExists(specialityGroupDIO.getName());
        SpecialityGroup specialityGroup = repository.save(mapper.map(specialityGroupDIO, SpecialityGroup.class));
        return mapper.map(specialityGroup, SpecialityGroupDTO.class);
    }

    public SpecialityGroupDTO update(SpecialityGroupDTO specialityGroupDIO) {
        checkNameExists(specialityGroupDIO.getName());
        SpecialityGroup specialityGroup = repository.save(mapper.map(specialityGroupDIO, SpecialityGroup.class));
        return mapper.map(specialityGroup, SpecialityGroupDTO.class);
    }

    public SpecialityGroup findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Speciality group with id " + id + " not found"));
    }

    private void checkNameExists(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException("Speciality group with name=" + name + " exist");
        }
    }
}
