package by.vstu.service.mapper;

import by.vstu.dto.abiturient.AbiturientDOO;
import by.vstu.model.abiturient.Abiturient;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class AbiturientDTOMapper implements EntityToDTOMapper<Object, Abiturient, AbiturientDOO> {

    private final ModelMapper mapper = new ModelMapper();

    private final AbiturientAdditionalInfoDTOMapper additionalInfoDTOMapper;
    private final AbiturientEducationInfoDTOMapper educationInfoDTOMapper;
    private final AbiturientProfileDTOMapper profileDTOMapper;
    private final AbiturientAddressInfoDTOMapper addressInfoDTOMapper;
    private final AbiturientCompetitionInfoDTOMapper competitionInfoDTOMapper;

    public AbiturientDTOMapper(AbiturientAdditionalInfoDTOMapper additionalInfoDTOMapper, AbiturientEducationInfoDTOMapper educationInfoDTOMapper,
                               AbiturientProfileDTOMapper profileDTOMapper, AbiturientAddressInfoDTOMapper addressInfoDTOMapper,
                               AbiturientCompetitionInfoDTOMapper competitionInfoDTOMapper) {
        this.additionalInfoDTOMapper = additionalInfoDTOMapper;
        this.educationInfoDTOMapper = educationInfoDTOMapper;
        this.profileDTOMapper = profileDTOMapper;
        this.addressInfoDTOMapper = addressInfoDTOMapper;
        this.competitionInfoDTOMapper = competitionInfoDTOMapper;

        mapper.addMappings(new PropertyMap<Abiturient, AbiturientDOO>() {
            protected void configure() {
                skip().setCompetitionInfo(null);
            }
        });

    }


    @Override
    public AbiturientDOO toDTO(Abiturient entity, Object... args) {
        AbiturientDOO doo = mapper.map(entity, AbiturientDOO.class);
        if (entity.getPersonalInfoFilled()) {
            doo.setProfileInfo(profileDTOMapper.toDTO(entity.getAbiturientProfile()));
        }
        if (entity.getAddressInfoFilled()) {
            doo.setAddressInfo(addressInfoDTOMapper.toDTO(entity.getAbiturientAddress()));
        }
        if (entity.getAdditionalInfoFilled()) {
            doo.setAdditionalInfo(additionalInfoDTOMapper.toDTO(entity.getAbiturientAdditionalInfo()));
        }
        if (entity.getEducationInfoFilled()) {
            doo.setEducationInfo(educationInfoDTOMapper.toDTO(entity.getAbiturientEducationInfo()));
        }
        if (entity.getCompetitionInfoFilled()) {
            doo.setCompetitionInfo(competitionInfoDTOMapper.toDTO(entity.getCompetitionInfo()));
        }
        return doo;
    }

    @Override
    public Abiturient toEntity(Object dto, Object... args) {
        return null;
    }
}
