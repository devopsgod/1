package by.vstu.service.mapper;

import by.vstu.dto.abiturient.AbiturientAdditionalInfoDTO;
import by.vstu.model.abiturient.Abiturient;
import by.vstu.model.abiturient.AbiturientAdditionalInfo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AbiturientAdditionalInfoDTOMapper
        implements EntityToDTOMapper<AbiturientAdditionalInfoDTO, AbiturientAdditionalInfo, AbiturientAdditionalInfoDTO> {

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public AbiturientAdditionalInfoDTO toDTO(AbiturientAdditionalInfo entity, Object... args) {
        return mapper.map(entity, AbiturientAdditionalInfoDTO.class);
    }

    @Override
    public AbiturientAdditionalInfo toEntity(AbiturientAdditionalInfoDTO dto, Object... args) {
        Abiturient abiturient = (Abiturient) args[0];
        AbiturientAdditionalInfo info = mapper.map(dto, AbiturientAdditionalInfo.class);
        if (abiturient.getAdditionalInfoFilled()) {
            info.setId(abiturient.getAbiturientAdditionalInfo().getId());
        }
        return info;
    }
}
