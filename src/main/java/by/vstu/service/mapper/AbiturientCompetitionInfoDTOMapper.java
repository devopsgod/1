package by.vstu.service.mapper;

import by.vstu.dto.abiturient.AbiturientCompetitionInfoDTO;
import by.vstu.model.abiturient.CompetitionInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AbiturientCompetitionInfoDTOMapper
        implements EntityToDTOMapper<AbiturientCompetitionInfoDTO, CompetitionInfo, AbiturientCompetitionInfoDTO> {

    private final SpecialityDTOMapper specialityDTOMapper;
    private final DocumentDTOMapper documentDTOMapper;

    @Override
    public AbiturientCompetitionInfoDTO toDTO(CompetitionInfo entity, Object... args) {
        AbiturientCompetitionInfoDTO dto = new AbiturientCompetitionInfoDTO();
        if (!CollectionUtils.isEmpty(entity.getDocuments())) {
            dto.setDocuments(entity.getDocuments().stream().map(documentDTOMapper::toDTO).collect(Collectors.toSet()));
        }
        dto.setSpecialities(entity.getSpecialities().stream()
                .map(sp -> specialityDTOMapper.toDTO(sp.getSpeciality())).collect(Collectors.toCollection(LinkedHashSet::new)));
        return dto;
    }

    @Override
    public CompetitionInfo toEntity(AbiturientCompetitionInfoDTO dto, Object... args) {
        return null;
    }
}
