package by.vstu.dto.dictionary.speciality;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SpecialitySumDTO {

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long specialityId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String speciality;

    @NotNull
    private Double sum;

    @NotNull
    private Double halfBall;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CompetitionStatusType status;
}
