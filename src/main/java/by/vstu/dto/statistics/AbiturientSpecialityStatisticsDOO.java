package by.vstu.dto.statistics;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class AbiturientSpecialityStatisticsDOO {

    private Integer countWithSpeciality;

    private Integer countWithoutSpeciality;

    private Integer summaryCountApproved;

    private Set<SpecialityStatisticsDOO> specialities = new HashSet<>();
}
