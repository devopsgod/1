package by.vstu.service.mapper;

import by.vstu.dto.dictionary.education.EducationInstitutionDTO;
import by.vstu.dto.dictionary.education.EducationTypeDTO;
import by.vstu.dto.dictionary.education.EstablishmentCityDTO;
import by.vstu.model.dictionary.education.EducationInstitution;
import by.vstu.service.dictionary.EducationTypeService;
import by.vstu.service.dictionary.EstablishmentCityService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class EducationInstitutionDTOMapper
        implements EntityToDTOMapper<EducationInstitutionDTO, EducationInstitution, EducationInstitutionDTO> {

    private final ModelMapper mapper = new ModelMapper();

    private final EstablishmentCityService establishmentCityService;

    private final EducationTypeService educationTypeService;

    public EducationInstitutionDTOMapper(EstablishmentCityService establishmentCityService, EducationTypeService educationTypeService) {
        this.establishmentCityService = establishmentCityService;
        this.educationTypeService = educationTypeService;

        mapper.addMappings(new PropertyMap<EducationInstitutionDTO, EducationInstitution>() {
            protected void configure() {
                skip().setId(null);
                skip().setEstablishmentCity(null);
                skip().setEducationType(null);
            }
        });
        mapper.addMappings(new PropertyMap<EducationInstitution, EducationInstitutionDTO>() {
            protected void configure() {
                skip().setEstablishmentCity(null);
                skip().setType(null);
            }
        });
    }

    @Override
    public EducationInstitutionDTO toDTO(EducationInstitution entity, Object... args) {
        EducationInstitutionDTO doo = mapper.map(entity, EducationInstitutionDTO.class);
        doo.setEstablishmentCity(mapper.map(entity.getEstablishmentCity(), EstablishmentCityDTO.class));
        doo.setType(mapper.map(entity.getEducationType(), EducationTypeDTO.class));
        return doo;
    }

    @Override
    public EducationInstitution toEntity(EducationInstitutionDTO dto, Object... args) {
        EducationInstitution institution = mapper.map(dto, EducationInstitution.class);
        institution.setEstablishmentCity(establishmentCityService.findByIdNotNull(dto.getEstCityId()));
        institution.setEducationType(educationTypeService.findByIdNotNull(dto.getTypeId()));
        return institution;
    }
}
