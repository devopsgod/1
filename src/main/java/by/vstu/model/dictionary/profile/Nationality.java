package by.vstu.model.dictionary.profile;

import by.vstu.model.dictionary.DictionaryNamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "nationality")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "n_id")),
        @AttributeOverride(name = "name", column = @Column(name = "n_name", length = 75, nullable = false))})
public class Nationality extends DictionaryNamedEntity {
}
