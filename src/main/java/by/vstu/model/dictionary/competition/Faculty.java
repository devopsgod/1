package by.vstu.model.dictionary.competition;

import by.vstu.model.dictionary.DictionaryNamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "faculty")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "f_id")),
        @AttributeOverride(name = "name", column = @Column(name = "f_name", length = 150, nullable = false))})
public class Faculty extends DictionaryNamedEntity {
}
