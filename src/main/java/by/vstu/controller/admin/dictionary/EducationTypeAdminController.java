package by.vstu.controller.admin.dictionary;

import by.vstu.dto.dictionary.education.EducationTypeDTO;
import by.vstu.service.dictionary.EducationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/dictionary/education-type")
public class EducationTypeAdminController {

    private final EducationTypeService educationTypeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EducationTypeDTO create(@Valid @RequestBody EducationTypeDTO educationTypeDIO) {
        return educationTypeService.create(educationTypeDIO);
    }

    @PutMapping("/{id}")
    public EducationTypeDTO update(@PathVariable Long id, @Valid @RequestBody EducationTypeDTO educationTypeDIO) {
        return educationTypeService.update(id, educationTypeDIO);
    }
}
