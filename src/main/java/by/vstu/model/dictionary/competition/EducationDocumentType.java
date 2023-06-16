package by.vstu.model.dictionary.competition;

import by.vstu.model.dictionary.DictionaryNamedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ed_doc_type")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "edt_id")),
        @AttributeOverride(name = "name", column = @Column(name = "edt_name", length = 70, nullable = false))})
public class EducationDocumentType extends DictionaryNamedEntity {

    @Column(name = "edt_is_internal")
    private Boolean internal;
}
