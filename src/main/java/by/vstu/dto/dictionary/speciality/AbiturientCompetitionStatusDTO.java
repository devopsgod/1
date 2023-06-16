package by.vstu.dto.dictionary.speciality;

import lombok.Data;

import java.util.List;

@Data
public class AbiturientCompetitionStatusDTO {

    private Long abiturientId;

    private Double sum;

    List<SpecialitySumDTO> specialities;
}
