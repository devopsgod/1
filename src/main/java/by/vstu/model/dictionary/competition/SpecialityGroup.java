package by.vstu.model.dictionary.competition;

import by.vstu.model.dictionary.DictionaryNamedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "speciality_group")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "sg_id")),
        @AttributeOverride(name = "name", column = @Column(name = "sg_name", length = 75, nullable = false))})
public class SpecialityGroup extends DictionaryNamedEntity {

    @Column(name = "sg_is_multi_select", nullable = false)
    private Boolean multiSelect;
}
