package by.vstu.controller.dictionary;

import by.vstu.dto.dictionary.document.SubjectDTO;
import by.vstu.service.dictionary.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/contains")
    public Page<SubjectDTO> getSubjectByName(@RequestParam(value = "fragment") String fragment, Pageable pageable) {
        return subjectService.findByNamePageable(fragment, pageable);
    }
}
