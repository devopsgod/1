package by.vstu.repository.dictionary;

import by.vstu.model.dictionary.competition.EducationDocumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EducationDocumentTypeRepository extends DictionaryRepository<EducationDocumentType> {

    Page<EducationDocumentType> findByInternalFalseAndNameIgnoreCaseContaining(String name, Pageable pageable);
}
