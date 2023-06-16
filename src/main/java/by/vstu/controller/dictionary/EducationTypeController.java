package by.vstu.controller.dictionary;

import by.vstu.dto.dictionary.education.EducationTypeDTO;
import by.vstu.service.dictionary.EducationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/education-type")
public class EducationTypeController {

    private final EducationTypeService educationTypeService;

    @GetMapping(value = "/contains")
    public Page<EducationTypeDTO> getEducationTypeByName(@RequestParam(value = "fragment") String fragment, Pageable pageable) {
        return educationTypeService.findByNamePageable(fragment, pageable);
    }
}
