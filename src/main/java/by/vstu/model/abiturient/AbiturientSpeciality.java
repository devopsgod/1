package by.vstu.model.abiturient;

import by.vstu.model.PersistentEntity;
import by.vstu.model.dictionary.competition.Speciality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "abiturient_speciality")
@AttributeOverride(name = "id", column = @Column(name = "as_id"))
public class AbiturientSpeciality extends PersistentEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "a_id")
    private Abiturient abiturient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sp_id")
    private Speciality speciality;

    @Column(name = "as_order")
    private Long order;
}
