package by.vstu.model.dictionary.education;

import by.vstu.model.dictionary.DictionaryNamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "language")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "l_id")),
        @AttributeOverride(name = "name", column = @Column(name = "l_name", length = 75, nullable = false))})
public class Language extends DictionaryNamedEntity {
}
