package by.vstu.controller.admin.dictionary;

import by.vstu.dto.dictionary.document.EducationDocumentTypeDTO;
import by.vstu.service.dictionary.EducationDocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/dictionary/education-document-type")
public class EducationDocumentTypeAdminController {

    private final EducationDocumentTypeService educationDocumentTypeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EducationDocumentTypeDTO create(@Valid @RequestBody EducationDocumentTypeDTO documentTypeDIO) {
        return educationDocumentTypeService.create(documentTypeDIO);
    }

    @PutMapping("/{id}")
    public EducationDocumentTypeDTO update(@PathVariable Long id, @Valid @RequestBody EducationDocumentTypeDTO documentTypeDIO) {
        return educationDocumentTypeService.update(id, documentTypeDIO);
    }
}
