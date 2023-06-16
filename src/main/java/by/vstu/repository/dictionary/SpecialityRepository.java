package by.vstu.repository.dictionary;

import by.vstu.model.dictionary.competition.EducationForm;
import by.vstu.model.dictionary.competition.EducationTime;
import by.vstu.model.dictionary.competition.Speciality;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecialityRepository extends DictionaryRepository<Speciality> {

    @Query("SELECT s FROM Speciality s " +
            "WHERE s.educationForm = :edForm AND s.educationTime = :edTime AND s.faculty.id = :fId ORDER BY s.name")
    List<Speciality> searchSpeciality(@Param("edForm") EducationForm educationForm,
                                      @Param("edTime") EducationTime educationTime,
                                      @Param("fId") Long facultyId);
}
