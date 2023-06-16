package by.vstu.model.dictionary;

import by.vstu.model.PersistentEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class DictionaryNamedEntity extends PersistentEntity {

    @Column(length = 50, nullable = false)
    private String name;
}
