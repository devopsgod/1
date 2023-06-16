package by.vstu.service.mapper;

import by.vstu.dto.abiturient.AbiturientProfileDTO;
import by.vstu.dto.dictionary.profile.DocumentTypeDTO;
import by.vstu.dto.dictionary.profile.NationalityDTO;
import by.vstu.model.abiturient.Abiturient;
import by.vstu.model.abiturient.AbiturientProfile;
import by.vstu.service.dictionary.DocumentTypeService;
import by.vstu.service.dictionary.NationalityService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class AbiturientProfileDTOMapper
        implements EntityToDTOMapper<AbiturientProfileDTO, AbiturientProfile, AbiturientProfileDTO> {

    private final ModelMapper mapper = new ModelMapper();

    private final NationalityService nationalityService;
    private final DocumentTypeService documentTypeService;

    public AbiturientProfileDTOMapper(NationalityService nationalityService, DocumentTypeService documentTypeService) {
        this.nationalityService = nationalityService;
        this.documentTypeService = documentTypeService;

        mapper.addMappings(new PropertyMap<AbiturientProfileDTO, AbiturientProfile>() {
            protected void configure() {
                skip().setId(null);
                skip().setNationality(null);
                skip().setDocumentType(null);
            }
        });
        mapper.addMappings(new PropertyMap<AbiturientProfile, AbiturientProfileDTO>() {
            protected void configure() {
                skip().setNationality(null);
                skip().setDocumentType(null);
            }
        });
    }

    @Override
    public AbiturientProfileDTO toDTO(AbiturientProfile entity, Object... args) {
        AbiturientProfileDTO doo = mapper.map(entity, AbiturientProfileDTO.class);
        doo.setNationality(mapper.map(entity.getNationality(), NationalityDTO.class));
        doo.setDocumentType(mapper.map(entity.getDocumentType(), DocumentTypeDTO.class));
        return doo;
    }

    @Override
    public AbiturientProfile toEntity(AbiturientProfileDTO dto, Object... args) {
        AbiturientProfile profile = mapper.map(dto, AbiturientProfile.class);
        profile.setNationality(nationalityService.findByIdNotNull(dto.getNationalityId()));
        profile.setDocumentType(documentTypeService.findByIdNotNull(dto.getDocumentTypeId()));

        Abiturient abiturient = (Abiturient) args[0];
        if (abiturient.getPersonalInfoFilled()) {
            profile.setId(abiturient.getAbiturientProfile().getId());
        }
        return profile;
    }
}
