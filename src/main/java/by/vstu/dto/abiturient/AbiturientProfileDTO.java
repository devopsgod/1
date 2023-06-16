package by.vstu.dto.abiturient;

import by.vstu.dto.dictionary.profile.DocumentTypeDTO;
import by.vstu.dto.dictionary.profile.NationalityDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class AbiturientProfileDTO {

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long documentTypeId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private DocumentTypeDTO documentType;

    @NotNull
    @Size(max = 10)
    private String documentSeria;

    @NotNull
    @Size(max = 50)
    private String documentNumber;

    @NotNull
    private Date documentDate;

    private String documentOrgan;

    @NotEmpty
    @Size(max = 50)
    private String firstName;

    @NotEmpty
    @Size(max = 50)
    private String firstNameEn;

    @NotEmpty
    @Size(max = 50)
    private String lastName;

    @NotEmpty
    @Size(max = 50)
    private String lastNameEn;

    @Size(max = 50)
    private String middleName;

    @NotNull
    private Date birthDate;

    @NotEmpty
    @Size(max = 200)
    private String birthPlace;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long nationalityId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private NationalityDTO nationality;

    @Size(max = 20)
    private String identificationNumber;

    @NotNull
    private Byte sex;
}
