package by.vstu.dto.abiturient;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
public class AbiturientAdditionalInfoDTO {

    @Size(max = 150)
    private String fatherFIO;

    @Size(max = 150)
    private String fatherWork;

    @Size(max = 50)
    private String fatherPhone;

    @Size(max = 150)
    private String motherFIO;

    @Size(max = 150)
    private String motherWork;

    @Size(max = 50)
    private String motherPhone;

    @Max(20)
    private Byte childCount;

    @Size(max = 100)
    private String workPlace;

    @Max(35)
    private Byte experience;

    private Boolean reAdmission;
}
