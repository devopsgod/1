package by.vstu.repository.dictionary;

import by.vstu.model.abiturient.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    void removeAllByAbiturientId(Long abiturientId);
}
