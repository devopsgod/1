package by.vstu.controller.dictionary;

import by.vstu.dto.dictionary.education.EducationInstitutionDTO;
import by.vstu.service.dictionary.EducationInstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/education-institution")
public class EducationInstitutionController {

    private final EducationInstitutionService educationInstitutionService;

    @GetMapping(value = "/contains")
    public Page<EducationInstitutionDTO> getEducationDocumentTypeByName(@RequestParam(value = "fragment") String fragment, Pageable pageable) {
        return educationInstitutionService.findByNamePageable(fragment, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EducationInstitutionDTO create(@Valid @RequestBody EducationInstitutionDTO educationInstitutionDIO) {
        return educationInstitutionService.create(educationInstitutionDIO, false);
    }
}
