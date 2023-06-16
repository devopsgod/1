package by.vstu.service.mapper;

import by.vstu.dto.abiturient.DocumentDTO;
import by.vstu.dto.dictionary.document.EducationDocumentTypeDTO;
import by.vstu.dto.dictionary.document.SubjectDTO;
import by.vstu.model.abiturient.Document;
import by.vstu.model.dictionary.competition.EducationDocumentType;
import by.vstu.model.dictionary.competition.Subject;
import by.vstu.service.dictionary.EducationDocumentTypeService;
import by.vstu.service.dictionary.SubjectService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DocumentDTOMapper implements EntityToDTOMapper<DocumentDTO, Document, DocumentDTO> {

    private final ModelMapper mapper = new ModelMapper();

    private final EducationDocumentTypeService documentTypeService;
    private final SubjectService subjectService;

    public DocumentDTOMapper(EducationDocumentTypeService documentTypeService, SubjectService subjectService) {
        this.documentTypeService = documentTypeService;
        this.subjectService = subjectService;

        mapper.addMappings(new PropertyMap<DocumentDTO, Document>() {
            protected void configure() {
                skip().setId(null);
                skip().setEducationDocumentType(null);
                skip().setSubject(null);
            }
        });
        mapper.addMappings(new PropertyMap<Document, DocumentDTO>() {
            protected void configure() {
                skip().setDocumentId(null);
                skip().setEducationDocumentType(null);
                skip().setSubject(null);
            }
        });
    }

    @Override
    public DocumentDTO toDTO(Document entity, Object... args) {
        DocumentDTO doo = mapper.map(entity, DocumentDTO.class);
        doo.setDocumentId(entity.getId());
        doo.setEducationDocumentType(mapper.map(entity.getEducationDocumentType(), EducationDocumentTypeDTO.class));
        if (Objects.nonNull(entity.getSubject())) {
            doo.setSubject(mapper.map(entity.getSubject(), SubjectDTO.class));
        }
        return doo;
    }

    @Override
    public Document toEntity(DocumentDTO dto, Object... args) {
        Document document = mapper.map(dto, Document.class);
        document.setId(dto.getDocumentId());
        document.setEducationDocumentType(mapper.map(documentTypeService.findByIdNotNull(dto.getEducationDocumentTypeId()), EducationDocumentType.class));
        if (Objects.nonNull(dto.getSubjectId())) {
            document.setSubject(mapper.map(subjectService.findByIdNotNull(dto.getSubjectId()), Subject.class));
        }
        return document;
    }
}
