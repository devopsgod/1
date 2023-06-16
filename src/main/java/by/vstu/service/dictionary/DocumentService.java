package by.vstu.service.dictionary;

import by.vstu.dto.abiturient.DocumentDTO;
import by.vstu.exception.BusinessException;
import by.vstu.exception.EntityNotFoundException;
import by.vstu.model.abiturient.Abiturient;
import by.vstu.model.abiturient.Document;
import by.vstu.model.abiturient.DocumentScaleType;
import by.vstu.repository.dictionary.DocumentRepository;
import by.vstu.service.mapper.DocumentDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository repository;

    private final DocumentDTOMapper mapper;

    public Set<Document> convertDocuments(Abiturient abiturient, Set<DocumentDTO> documentsDIO) {
        repository.removeAllByAbiturientId(abiturient.getId());
        Set<Document> documents = new HashSet<>();

        for (DocumentDTO documentDTO : documentsDIO) {
            Document document = mapper.toEntity(documentDTO);
            if (documentDTO.getSubjectId() != null) {
                document.setMarkTen(getAverageMarkTen(documentDTO.getMarks()));
            } else {
                if (documentDTO.getScale() == null) {
                    throw new BusinessException("Scale can't be empty!");
                }
                document.setNameUO(documentDTO.getNameUO());
                document.setScale(documentDTO.getScale());
                document.setMarkTen(documentDTO.getScale().equals(DocumentScaleType.FIVE_POINT) ?
                        getAverageMarkTen(documentDTO.getMarks()) * 2 : getAverageMarkTen(documentDTO.getMarks()));
            }

            document.setAbiturient(abiturient);
            document.setMarkHundred(Math.round(document.getMarkTen() * 10));
            documents.add(repository.save(document));
        }
        return documents;
    }

    private Double getAverageMarkTen(int[] marks) {
        if (marks.length == 1) {
            return marks[0] / 10.0;
        }
        Double averageMark = 0.0;
        for (int i = 0; i < marks.length; i++) {
            averageMark += marks[i] * (i + 1);
        }
        return Math.round(averageMark / IntStream.of(marks).sum() * 100000.0) / 100000.0;
    }

    public Document findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Document with id " + id + " not found"));
    }
}
