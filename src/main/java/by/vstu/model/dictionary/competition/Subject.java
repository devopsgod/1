package by.vstu.model.dictionary.competition;

import by.vstu.model.dictionary.DictionaryNamedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "subject")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "s_id")),
        @AttributeOverride(name = "name", column = @Column(name = "s_name", length = 150, nullable = false))})
public class Subject extends DictionaryNamedEntity {

    @Column(name = "s_is_internal")
    private Boolean internal;
}
