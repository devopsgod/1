package by.vstu.service;

import by.vstu.dto.statistics.AbiturientSpecialityStatisticsDOO;
import by.vstu.dto.statistics.SpecialityStatisticsDOO;
import by.vstu.model.abiturient.Abiturient;
import by.vstu.model.dictionary.competition.Speciality;
import by.vstu.service.abiturient.AbiturientService;
import by.vstu.service.dictionary.SpecialityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final AbiturientService abiturientService;
    private final SpecialityService specialityService;

    public AbiturientSpecialityStatisticsDOO getStatisticsBySpecialities() {
        AbiturientSpecialityStatisticsDOO abiturientSpecialityStatisticsDOO = new AbiturientSpecialityStatisticsDOO();
        List<Abiturient> abiturients = abiturientService.getInternalAbiturientsWithSpecialities();

        abiturientSpecialityStatisticsDOO.setCountWithSpeciality(abiturients.size());
        abiturientSpecialityStatisticsDOO.setCountWithoutSpeciality(abiturientService.getInternalAbiturientsWithoutSpecialities().size());

        List<Speciality> specialities = specialityService.getAll();
        for (Speciality speciality : specialities) {
            SpecialityStatisticsDOO speStat = new SpecialityStatisticsDOO(speciality);

            Long fullCount = specialityService.countInternalAbiturientsBySpeciality(speciality.getId());
            Long countApproved = specialityService.countInternalApprovedAbiturientsBySpeciality(speciality.getId());

            speStat.setCountAbiturients(fullCount);
            speStat.setCountApproved(countApproved);
            abiturientSpecialityStatisticsDOO.getSpecialities().add(speStat);
        }
        abiturientSpecialityStatisticsDOO.setSummaryCountApproved(abiturientService.countInternalApprovedAbiturient());

        return abiturientSpecialityStatisticsDOO;
    }
}
