package by.vstu.model.abiturient;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "abiturient")
public class Abiturient {

    @Id
    @GenericGenerator(name = "abiturient_id", strategy = "by.vstu.model.generator.AbiturientIdGenerator")
    @GeneratedValue(generator = "abiturient_id")
    @Column(name = "a_id")
    private Long id;

    @NotNull
    @Column(name = "a_email", length = 100, unique = true)
    private String email;

    @NotNull
    @Column(name = "a_is_profile_filled")
    private Boolean personalInfoFilled = false;

    @NotNull
    @Column(name = "a_is_address_filled")
    private Boolean addressInfoFilled = false;

    @NotNull
    @Column(name = "a_is_education_filled")
    private Boolean educationInfoFilled = false;

    @NotNull
    @Column(name = "a_is_competition_info_filled")
    private Boolean competitionInfoFilled = false;

    @NotNull
    @Column(name = "a_is_additional_info_filled")
    private Boolean additionalInfoFilled = false;

    @NotNull
    @Column(name = "a_is_profile_approved")
    private Boolean profileApproved = false;

    @NotNull
    @Column(name = "a_is_deleted")
    private Boolean deleted = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ab_p_id")
    private AbiturientProfile abiturientProfile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ab_adr_id")
    private AbiturientAddress abiturientAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ab_ed_id")
    private AbiturientEducationInfo abiturientEducationInfo;

    @Embedded
    private CompetitionInfo competitionInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ab_add_id")
    private AbiturientAdditionalInfo abiturientAdditionalInfo;
}
