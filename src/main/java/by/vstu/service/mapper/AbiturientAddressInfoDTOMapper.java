package by.vstu.service.mapper;

import by.vstu.dto.abiturient.AbiturientAddressInfoDTO;
import by.vstu.model.abiturient.Abiturient;
import by.vstu.model.abiturient.AbiturientAddress;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AbiturientAddressInfoDTOMapper
        implements EntityToDTOMapper<AbiturientAddressInfoDTO, AbiturientAddress, AbiturientAddressInfoDTO> {

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public AbiturientAddressInfoDTO toDTO(AbiturientAddress entity, Object... args) {
        return mapper.map(entity, AbiturientAddressInfoDTO.class);
    }

    @Override
    public AbiturientAddress toEntity(AbiturientAddressInfoDTO dto, Object... args) {
        AbiturientAddress entity = mapper.map(dto, AbiturientAddress.class);

        Abiturient abiturient = (Abiturient) args[0];
        if (abiturient.getAddressInfoFilled()) {
            entity.setId(abiturient.getAbiturientAddress().getId());
        }
        return entity;
    }
}
