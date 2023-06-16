package by.vstu.service.dictionary;

import by.vstu.dto.dictionary.education.EducationInstitutionDTO;
import by.vstu.exception.BusinessException;
import by.vstu.exception.EntityNotFoundException;
import by.vstu.model.dictionary.education.EducationInstitution;
import by.vstu.repository.dictionary.EducationInstitutionRepository;
import by.vstu.service.mapper.EducationInstitutionDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationInstitutionService {

    private final EducationInstitutionRepository repository;

    private final EducationTypeService educationTypeService;

    private final EstablishmentCityService establishmentCityService;

    private final EducationInstitutionDTOMapper mapper;

    public Page<EducationInstitutionDTO> findByNamePageable(String name, Pageable pageable) {
        return repository.findByTempFalseAndNameIgnoreCaseContaining(name, pageable).map(mapper::toDTO);
    }

    public EducationInstitutionDTO create(EducationInstitutionDTO institutionDIO, boolean isAdmin) {
        EducationInstitution educationInstitution = checkNameExists(institutionDIO.getName(), institutionDIO.getEstCityId(), institutionDIO.getTypeId());
        educationInstitution.setName(institutionDIO.getName());
        educationInstitution.setTemp(!isAdmin);
        educationInstitution.setEducationType(educationTypeService.findByIdNotNull(institutionDIO.getTypeId()));
        educationInstitution.setEstablishmentCity(establishmentCityService.findByIdNotNull(institutionDIO.getEstCityId()));
        return mapper.toDTO(repository.save(educationInstitution));
    }

    public EducationInstitutionDTO update(Long id, EducationInstitutionDTO institutionDIO) {
        EducationInstitution educationInstitution = findByIdNotNull(id);
        educationInstitution.setName(institutionDIO.getName());
        educationInstitution.setEducationType(educationTypeService.findByIdNotNull(institutionDIO.getTypeId()));
        educationInstitution.setEstablishmentCity(establishmentCityService.findByIdNotNull(institutionDIO.getEstCityId()));
        return mapper.toDTO(repository.save(educationInstitution));
    }

    public EducationInstitution findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Education institution with id " + id + " not found"));
    }

    private EducationInstitution checkNameExists(String name, Long cityId, Long typeId) {
        EducationInstitution educationInstitution = repository.findByNameIgnoreCaseAndEstablishmentCity_IdAndEducationType_Id(name, cityId, typeId);
        if (educationInstitution != null && !educationInstitution.getTemp()) {
            throw new BusinessException("Education institution with name: " + name + " and cityId" + cityId + " and typeId=" + typeId + "exists");
        }
        return educationInstitution == null ? new EducationInstitution() : educationInstitution;
    }
}
