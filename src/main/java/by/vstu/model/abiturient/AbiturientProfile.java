package by.vstu.model.abiturient;

import by.vstu.model.PersistentEntity;
import by.vstu.model.dictionary.profile.DocumentType;
import by.vstu.model.dictionary.profile.Nationality;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "ab_profile")
@AttributeOverride(name = "id", column = @Column(name = "ab_p_id"))
public class AbiturientProfile extends PersistentEntity {

    @Column(name = "ab_p_first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "ab_p_last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "ab_p_first_name_en", length = 50, nullable = false)
    private String firstNameEn;

    @Column(name = "ab_p_last_name_en", length = 50, nullable = false)
    private String lastNameEn;

    @Column(name = "ab_p_middle_name", length = 50)
    private String middleName;

    @OneToOne(optional = false)
    @JoinColumn(name = "dt_id")
    private DocumentType documentType;

    @Column(name = "ab_p_document_seria", length = 10, nullable = false)
    private String documentSeria;

    @Column(name = "ab_p_document_number", length = 50, nullable = false)
    private String documentNumber;

    @Column(name = "ab_p_document_date", nullable = false)
    private Date documentDate;

    @Column(name = "ab_p_document_organ")
    private String documentOrgan;

    @Column(name = "ab_p_birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "ab_p_birth_place", length = 200, nullable = false)
    private String birthPlace;

    @OneToOne(optional = false)
    @JoinColumn(name = "n_id")
    private Nationality nationality;

    @Column(name = "ab_p_identification_number", length = 20)
    private String identificationNumber;

    @Column(name = "ab_p_sex", nullable = false)
    private Byte sex;
}
