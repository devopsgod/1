package by.vstu.model.abiturient;

import by.vstu.model.PersistentEntity;
import by.vstu.model.dictionary.competition.EducationDocumentType;
import by.vstu.model.dictionary.competition.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "document")
@AttributeOverride(name = "id", column = @Column(name = "d_id"))
public class Document extends PersistentEntity {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "a_id")
    private Abiturient abiturient;

    @OneToOne
    @JoinColumn(name = "s_id")
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "edt_id")
    private EducationDocumentType educationDocumentType;

    @Column(name = "d_seria", length = 5)
    private String seria;

    @Column(name = "d_number", length = 15)
    private String number;

    @Column(name = "d_name_uo", length = 250)
    private String nameUO;

    @Enumerated(EnumType.STRING)
    @Column(name = "d_scale")
    private DocumentScaleType scale;

    @NotNull
    @Column(name = "d_marks")
    private int[] marks;

    @Column(name = "d_mark_ten")
    private Double markTen;

    @Column(name = "d_mark_hundred")
    private Long markHundred;
}
