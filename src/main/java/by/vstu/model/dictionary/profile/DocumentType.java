package by.vstu.model.dictionary.profile;

import by.vstu.model.dictionary.DictionaryNamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "document_type")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dt_id")),
        @AttributeOverride(name = "name", column = @Column(name = "dt_name", length = 100, nullable = false))})
public class DocumentType extends DictionaryNamedEntity {
}
