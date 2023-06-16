package by.vstu.service.dictionary;

import by.vstu.dto.dictionary.speciality.SpecialityDTO;
import by.vstu.dto.dictionary.speciality.SpecialitySearchDIO;
import by.vstu.dto.dictionary.speciality.SpecialitySumDTO;
import by.vstu.exception.BusinessException;
import by.vstu.exception.EntityNotFoundException;
import by.vstu.model.abiturient.Abiturient;
import by.vstu.model.abiturient.AbiturientSpeciality;
import by.vstu.model.dictionary.competition.Speciality;
import by.vstu.model.dictionary.competition.SpecialityGroup;
import by.vstu.repository.abiturient.AbiturientSpecialityRepository;
import by.vstu.repository.dictionary.SpecialityRepository;
import by.vstu.service.mapper.SpecialityDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialityService {

    private final SpecialityRepository repository;

    private final SpecialityDTOMapper mapper;

    private final AbiturientSpecialityRepository abiturientSpecialityRepository;

    private final Long INTERNAL_ABITURIENT_MIN_VALUE_ID = 50000L;

    public List<Speciality> getAll() {
        return repository.findAll();
    }

    public List<SpecialityDTO> getSpecialitiesBySearch(SpecialitySearchDIO searchDIO) {
        return repository.searchSpeciality(searchDIO.getEducationForm(), searchDIO.getEducationTime(), searchDIO.getFacultyId())
                .stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public Set<AbiturientSpeciality> convertSpecialities(Abiturient abiturient, Set<Long> specIds) {
        abiturientSpecialityRepository.removeAllByAbiturientId(abiturient.getId());
        Set<AbiturientSpeciality> specialities = new LinkedHashSet<>();
        SpecialityGroup currentGroup = null;
        long order = 0;
        for (Long specId : specIds) {
            Speciality speciality = findByIdNotNull(specId);
            if (Objects.isNull(currentGroup)) {
                currentGroup = speciality.getGroup();
            } else if (!currentGroup.getMultiSelect() && !specialities.isEmpty()) {
                throw new BusinessException("Choose only one speciality from this group");
            } else if (currentGroup.getMultiSelect() && currentGroup != speciality.getGroup()) {
                throw new BusinessException("Your specialities from another groups! See rules");
            }
            specialities.add(abiturientSpecialityRepository.save(new AbiturientSpeciality(abiturient, speciality, order++)));
        }
        return specialities;
    }

    public Speciality findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Speciality with id " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public Long countInternalAbiturientsBySpeciality(Long specialityId) {
        return abiturientSpecialityRepository.countBySpecialityIdAndAbiturientIdGreaterThan(specialityId, INTERNAL_ABITURIENT_MIN_VALUE_ID);
    }

    @Transactional(readOnly = true)
    public Long countInternalApprovedAbiturientsBySpeciality(Long specialityId) {
        return abiturientSpecialityRepository.countByAbiturientProfileApprovedIsTrueAndSpecialityIdAndAbiturientIdGreaterThan(specialityId, INTERNAL_ABITURIENT_MIN_VALUE_ID);
    }

    @Transactional
    public List<SpecialityDTO> updateSpecialitySum(List<SpecialitySumDTO> sum) {
        return sum.stream().map(sumDTO -> {
            Speciality speciality = findByIdNotNull(sumDTO.getSpecialityId());
            speciality.setCurrentSum(sumDTO.getSum());
            speciality.setHalfSum(sumDTO.getHalfBall());
            return mapper.toDTO(repository.save(speciality));
        }).collect(Collectors.toList());
    }
}
