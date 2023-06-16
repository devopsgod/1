package by.vstu.repository.dictionary;

import by.vstu.model.dictionary.competition.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubjectRepository extends DictionaryRepository<Subject> {

    Page<Subject> findByInternalFalseAndNameIgnoreCaseContaining(String name, Pageable pageable);
}
