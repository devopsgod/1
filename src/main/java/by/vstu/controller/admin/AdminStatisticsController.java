package by.vstu.controller.admin;

import by.vstu.dto.statistics.AbiturientSpecialityStatisticsDOO;
import by.vstu.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/statistics")
public class AdminStatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/speciality")
    public AbiturientSpecialityStatisticsDOO getAbiturientsSpecialityStatistic() {
        return statisticsService.getStatisticsBySpecialities();
    }
}
