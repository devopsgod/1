package by.vstu.controller.admin.dictionary;

import by.vstu.dto.dictionary.education.EducationInstitutionDTO;
import by.vstu.service.dictionary.EducationInstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/dictionary/education-institution")
public class EducationInstitutionAdminController {

    private final EducationInstitutionService educationInstitutionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EducationInstitutionDTO create(@Valid @RequestBody EducationInstitutionDTO educationInstitutionDIO) {
        return educationInstitutionService.create(educationInstitutionDIO, true);
    }

    @PutMapping("/{id}")
    public EducationInstitutionDTO update(@PathVariable Long id, @Valid @RequestBody EducationInstitutionDTO educationInstitutionDIO) {
        return educationInstitutionService.update(id, educationInstitutionDIO);
    }
}
