package by.vstu.service.mapper;

import by.vstu.dto.abiturient.AbiturientEducationInfoDTO;
import by.vstu.dto.dictionary.education.EducationLevelDTO;
import by.vstu.dto.dictionary.education.LanguageDTO;
import by.vstu.model.abiturient.Abiturient;
import by.vstu.model.abiturient.AbiturientEducationInfo;
import by.vstu.service.dictionary.EducationInstitutionService;
import by.vstu.service.dictionary.EducationLevelService;
import by.vstu.service.dictionary.LanguageService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class AbiturientEducationInfoDTOMapper
        implements EntityToDTOMapper<AbiturientEducationInfoDTO, AbiturientEducationInfo, AbiturientEducationInfoDTO> {

    private final ModelMapper mapper = new ModelMapper();

    private final EducationInstitutionDTOMapper educationInstitutionDTOMapper;
    private final EducationInstitutionService educationInstitutionService;
    private final EducationLevelService educationLevelService;
    private final LanguageService languageService;

    public AbiturientEducationInfoDTOMapper(EducationInstitutionDTOMapper educationInstitutionDTOMapper, EducationInstitutionService educationInstitutionService,
                                            EducationLevelService educationLevelService, LanguageService languageService) {
        this.educationInstitutionDTOMapper = educationInstitutionDTOMapper;
        this.educationInstitutionService = educationInstitutionService;
        this.educationLevelService = educationLevelService;
        this.languageService = languageService;

        mapper.addMappings(new PropertyMap<AbiturientEducationInfoDTO, AbiturientEducationInfo>() {
            protected void configure() {
                skip().setId(null);
                skip().setEducationLevel(null);
                skip().setEducationInstitution(null);
                skip().setLanguage(null);
            }
        });
        mapper.addMappings(new PropertyMap<AbiturientEducationInfo, AbiturientEducationInfoDTO>() {
            protected void configure() {
                skip().setEducationLevel(null);
                skip().setEducationInstitution(null);
                skip().setLanguage(null);
            }
        });
    }

    @Override
    public AbiturientEducationInfoDTO toDTO(AbiturientEducationInfo entity, Object... args) {
        AbiturientEducationInfoDTO doo = mapper.map(entity, AbiturientEducationInfoDTO.class);
        doo.setEducationInstitution(educationInstitutionDTOMapper.toDTO(entity.getEducationInstitution()));
        doo.setEducationLevel(mapper.map(entity.getEducationLevel(), EducationLevelDTO.class));
        doo.setLanguage(mapper.map(entity.getLanguage(), LanguageDTO.class));
        return doo;
    }

    @Override
    public AbiturientEducationInfo toEntity(AbiturientEducationInfoDTO dto, Object... args) {
        AbiturientEducationInfo info = mapper.map(dto, AbiturientEducationInfo.class);
        info.setEducationInstitution(educationInstitutionService.findByIdNotNull(dto.getEducationInstitutionId()));
        info.setEducationLevel(educationLevelService.findByIdNotNull(dto.getEducationLevelId()));
        info.setLanguage(languageService.findByIdNotNull(dto.getLanguageId()));

        Abiturient abiturient = (Abiturient) args[0];
        if (abiturient.getEducationInfoFilled()) {
            info.setId(abiturient.getAbiturientEducationInfo().getId());
        }
        return info;
    }
}
