package by.vstu.controller.dictionary;

import by.vstu.dto.dictionary.speciality.FacultyDTO;
import by.vstu.service.dictionary.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping("/contains")
    public Page<FacultyDTO> findByNamePageable(@RequestParam(value = "fragment") String fragment, Pageable pageable) {
        return facultyService.findByNamePageable(fragment, pageable);
    }
}
