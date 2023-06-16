package by.vstu.model.dictionary.education;

import by.vstu.model.dictionary.DictionaryNamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "education_type")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "et_id")),
        @AttributeOverride(name = "name", column = @Column(name = "et_name", length = 75, nullable = false))})
public class EducationType extends DictionaryNamedEntity {
}
