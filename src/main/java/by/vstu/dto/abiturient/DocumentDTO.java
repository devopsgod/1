package by.vstu.dto.abiturient;

import by.vstu.dto.dictionary.document.EducationDocumentTypeDTO;
import by.vstu.dto.dictionary.document.SubjectDTO;
import by.vstu.model.abiturient.DocumentScaleType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DocumentDTO {

    private Long documentId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long subjectId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private SubjectDTO subject;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long educationDocumentTypeId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EducationDocumentTypeDTO educationDocumentType;

    private DocumentScaleType scale;

    @Size(max = 5)
    private String seria;

    @Size(max = 15)
    private String number;

    private String nameUO;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double markTen;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long markHundred;

    @NotNull
    private int[] marks = new int[0]; //TODO переделать
}
