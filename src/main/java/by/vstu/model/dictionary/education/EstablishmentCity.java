package by.vstu.model.dictionary.education;

import by.vstu.model.dictionary.DictionaryNamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "establishment_city")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "ec_id")),
        @AttributeOverride(name = "name", column = @Column(name = "ec_name", length = 150, nullable = false))})
public class EstablishmentCity extends DictionaryNamedEntity {
}
