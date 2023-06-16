package by.vstu.dto.abiturient;

import by.vstu.dto.dictionary.speciality.SpecialityDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class AbiturientCompetitionInfoDTO {

    @Valid
    private Set<DocumentDTO> documents;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LinkedHashSet<Long> specialityIds;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LinkedHashSet<SpecialityDTO> specialities;
}
