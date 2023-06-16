package by.vstu.controller.admin.dictionary;

import by.vstu.dto.dictionary.education.EducationLevelDTO;
import by.vstu.service.dictionary.EducationLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/dictionary/education-level")
public class EducationLevelAdminController {

    private final EducationLevelService educationLevelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EducationLevelDTO create(@Valid @RequestBody EducationLevelDTO levelDIO) {
        return educationLevelService.create(levelDIO);
    }

    @PutMapping("/{id}")
    public EducationLevelDTO update(@PathVariable Long id, @Valid @RequestBody EducationLevelDTO levelDIO) {
        return educationLevelService.update(id, levelDIO);
    }
}
