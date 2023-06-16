package by.vstu.model.dictionary.competition;

import by.vstu.model.dictionary.DictionaryNamedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "speciality")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "sp_id")),
        @AttributeOverride(name = "name", column = @Column(name = "sp_name", length = 150, nullable = false))})
public class Speciality extends DictionaryNamedEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "sp_education_time", nullable = false)
    private EducationTime educationTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "sp_education_form", nullable = false)
    private EducationForm educationForm;

    @Column(name = "sp_is_free", nullable = false)
    private Boolean free;

    @OneToOne
    @JoinColumn(name = "f_id")
    private Faculty faculty;

    @OneToOne
    @JoinColumn(name = "sg_id")
    private SpecialityGroup group;

    @Column(name = "sp_plan", nullable = false)
    private Long plan;

    @Column(name = "sp_current_sum")
    private Double currentSum;

    @Column(name = "sp_half_sum")
    private Double halfSum;

    public String typeToString() {
        return getFree() ? ", за счет средств бюджета" : ", на условиях оплаты";
    }
}
