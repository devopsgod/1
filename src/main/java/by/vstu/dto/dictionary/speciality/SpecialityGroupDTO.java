package by.vstu.dto.dictionary.speciality;

import by.vstu.dto.dictionary.DictionaryNamedDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SpecialityGroupDTO extends DictionaryNamedDTO {

    private boolean multiSelect;
}
