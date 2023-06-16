package by.vstu.dto.dictionary.speciality;

import by.vstu.model.dictionary.competition.EducationForm;
import by.vstu.model.dictionary.competition.EducationTime;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SpecialitySearchDIO {

    @NotNull
    private EducationTime educationTime;

    @NotNull
    private EducationForm educationForm;

    @NotNull
    private Long facultyId;
}
