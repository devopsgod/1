package by.vstu.dto.abiturient;

import lombok.Data;

@Data
public class AbiturientDOO {

    private Long Id;

    private String email;

    private Boolean personalInfoFilled;

    private Boolean addressInfoFilled;

    private Boolean educationInfoFilled;

    private Boolean competitionInfoFilled;

    private Boolean additionalInfoFilled;

    private Boolean profileApproved;

    private AbiturientProfileDTO profileInfo;

    private AbiturientAddressInfoDTO addressInfo;

    private AbiturientEducationInfoDTO educationInfo;

    private AbiturientCompetitionInfoDTO competitionInfo;

    private AbiturientAdditionalInfoDTO additionalInfo;
}
