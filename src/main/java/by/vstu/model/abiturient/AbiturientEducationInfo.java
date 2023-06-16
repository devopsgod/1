package by.vstu.model.abiturient;

import by.vstu.model.PersistentEntity;
import by.vstu.model.dictionary.education.EducationInstitution;
import by.vstu.model.dictionary.education.EducationLevel;
import by.vstu.model.dictionary.education.Language;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "ab_education")
@AttributeOverride(name = "id", column = @Column(name = "ab_ed_id"))
public class AbiturientEducationInfo extends PersistentEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "el_id")
    private EducationLevel educationLevel;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ei_id")
    private EducationInstitution educationInstitution;

    @NotNull
    @Column(name = "ab_ed_end_year")
    private Integer endYear;

    @ManyToOne(optional = false)
    @JoinColumn(name = "l_id")
    private Language language;

    @NotNull
    @Column(name = "ab_ed_gold_medalist")
    private Boolean goldMedalist;

    @NotNull
    @Column(name = "ab_ed_honours")
    private Boolean honours;
}
