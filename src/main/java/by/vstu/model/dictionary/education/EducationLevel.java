package by.vstu.model.dictionary.education;

import by.vstu.model.dictionary.DictionaryNamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "education_level")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "el_id")),
        @AttributeOverride(name = "name", column = @Column(name = "el_name", length = 75, nullable = false))})
public class EducationLevel extends DictionaryNamedEntity {
}
