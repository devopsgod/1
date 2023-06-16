package by.vstu.controller.dictionary;

import by.vstu.dto.dictionary.education.EducationLevelDTO;
import by.vstu.service.dictionary.EducationLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/education-level")
public class EducationLevelController {

    private final EducationLevelService educationLevelService;

    @GetMapping(value = "/contains")
    public Page<EducationLevelDTO> getEducationLevelByName(@RequestParam(value = "fragment") String fragment, Pageable pageable) {
        return educationLevelService.findByNamePageable(fragment, pageable);
    }
}
