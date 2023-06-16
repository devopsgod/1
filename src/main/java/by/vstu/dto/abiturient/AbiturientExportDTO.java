package by.vstu.dto.abiturient;

import by.vstu.dto.dictionary.speciality.SpecialityDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class AbiturientExportDTO extends AbiturientExportObject {

    @NotNull
    private Long id;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Valid
    private Set<DocumentDTO> documents;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LinkedHashSet<Long> specialityIds;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LinkedHashSet<SpecialityDTO> specialities;

}
