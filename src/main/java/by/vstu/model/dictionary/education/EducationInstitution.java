package by.vstu.model.dictionary.education;

import by.vstu.model.dictionary.DictionaryNamedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "education_institution")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "ei_id")),
        @AttributeOverride(name = "name", column = @Column(name = "ei_name", nullable = false))})
public class EducationInstitution extends DictionaryNamedEntity {

    @ManyToOne
    @JoinColumn(name = "et_id")
    private EducationType educationType;

    @ManyToOne
    @JoinColumn(name = "ec_id")
    private EstablishmentCity establishmentCity;

    @Column(name = "ei_is_temp", nullable = false)
    private Boolean temp;

    public EducationInstitution(String name) {
        setName(name);
    }
}
