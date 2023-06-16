package by.vstu.repository.dictionary;

import by.vstu.model.dictionary.education.EducationInstitution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EducationInstitutionRepository extends DictionaryRepository<EducationInstitution> {

    @Query(value = "SELECT * FROM tuo WHERE temp = true AND 0 = (Select count(idAbitur) FROM tabitur WHERE tuo.iduo = tabitur.ed_inst_id)", nativeQuery = true)
    List<EducationInstitution> findUnusableEducationInstitutions();

    EducationInstitution findByNameAndEducationType_IdAndEstablishmentCity_Id(String name, Long edTypeId, Long extCityId);

    Page<EducationInstitution> findByTempFalseAndNameIgnoreCaseContaining(String fragment, Pageable pageable);

    EducationInstitution findByNameIgnoreCaseAndEstablishmentCity_IdAndEducationType_Id(String name, Long cityId, Long typeId);
}
