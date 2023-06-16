package by.vstu.controller.admin.dictionary;

import by.vstu.dto.dictionary.document.SubjectDTO;
import by.vstu.service.dictionary.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/dictionary/subject")
public class SubjectAdminController {

    private final SubjectService subjectService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectDTO create(@Valid @RequestBody SubjectDTO subjectDIO) {
        return subjectService.create(subjectDIO);
    }

    @PutMapping("/{id}")
    public SubjectDTO update(@PathVariable Long id, @Valid @RequestBody SubjectDTO subjectDIO) {
        return subjectService.update(id, subjectDIO);
    }
}
