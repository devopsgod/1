package by.vstu.dto.statistics;

import by.vstu.model.dictionary.competition.Speciality;
import lombok.Data;

@Data
public class SpecialityStatisticsDOO {

    private String educationForm;

    private String educationTime;

    private String speciality;

    private Long plan;

    private Long countAbiturients;

    private Long countApproved;

    public SpecialityStatisticsDOO(Speciality speciality) {
        setEducationForm(speciality.getEducationForm().getDisplayName());
        setEducationTime(speciality.getEducationTime().getDisplayName());
        setSpeciality(speciality.getName());
        setPlan(speciality.getPlan());
    }
}
