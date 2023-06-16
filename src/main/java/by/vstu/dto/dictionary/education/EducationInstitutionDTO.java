package by.vstu.dto.dictionary.education;

import by.vstu.dto.dictionary.DictionaryNamedDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EducationInstitutionDTO extends DictionaryNamedDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long typeId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EducationTypeDTO type;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long estCityId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EstablishmentCityDTO establishmentCity;
}
