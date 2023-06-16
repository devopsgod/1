package by.vstu.service;

import by.vstu.repository.dictionary.EducationInstitutionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchedulerService {

    private final EducationInstitutionRepository educationInstitutionRepository;

    private final String CRON_FOR_DELETE = "0 0 * * * *"; //Every day at 00:00:00

    @Scheduled(cron = CRON_FOR_DELETE)
    public void removeUnusable() {
        log.info("Scheduler: Unusable education institution was removed");
        educationInstitutionRepository.deleteAll(educationInstitutionRepository.findUnusableEducationInstitutions());
    }

}
