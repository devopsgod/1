package by.vstu.dto.dictionary.speciality;

import by.vstu.dto.dictionary.DictionaryNamedDTO;
import by.vstu.model.dictionary.competition.EducationForm;
import by.vstu.model.dictionary.competition.EducationTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SpecialityDTO extends DictionaryNamedDTO {

    private EducationTime educationTime;

    private EducationForm educationForm;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long facultyId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private FacultyDTO faculty;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long groupId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private SpecialityGroupDTO group;

    private Long plan;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long currentSum;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long halfSum;
}
