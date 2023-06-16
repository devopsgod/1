package by.vstu.model.abiturient;

import by.vstu.model.PersistentEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "ab_additional")
@AttributeOverride(name = "id", column = @Column(name = "ab_add_id"))
public class AbiturientAdditionalInfo extends PersistentEntity {

    @Column(name = "ab_add_father_fio", length = 150)
    private String fatherFIO;

    @Column(name = "ab_add_father_work", length = 150)
    private String fatherWork;

    @Column(name = "ab_add_father_phone", length = 50)
    private String fatherPhone;

    @Column(name = "ab_add_mother_fio", length = 150)
    private String motherFIO;

    @Column(name = "ab_add_mother_work", length = 150)
    private String motherWork;

    @Column(name = "ab_add_mother_phone", length = 50)
    private String motherPhone;

    @Column(name = "ab_add_child_count")
    private Byte childCount;

    @Column(name = "ab_add_work_place", length = 150)
    private String workPlace;

    @Column(name = "ab_add_experience")
    private Byte experience;

    @Column(name = "ab_add_is_re_admission")
    private Boolean reAdmission;
}
