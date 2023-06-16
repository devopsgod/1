package by.vstu.dto.abiturient;

import by.vstu.dto.dictionary.education.EducationInstitutionDTO;
import by.vstu.dto.dictionary.education.EducationLevelDTO;
import by.vstu.dto.dictionary.education.LanguageDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AbiturientEducationInfoDTO {

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long educationLevelId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EducationLevelDTO educationLevel;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long educationInstitutionId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EducationInstitutionDTO educationInstitution;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long languageId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LanguageDTO language;

    @NotNull
    private Integer endYear;

    @NotNull
    private Boolean goldMedalist;

    @NotNull
    private Boolean honours;
}
