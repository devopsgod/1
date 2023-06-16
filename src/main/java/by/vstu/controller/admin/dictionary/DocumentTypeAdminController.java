package by.vstu.controller.admin.dictionary;

import by.vstu.dto.dictionary.profile.DocumentTypeDTO;
import by.vstu.service.dictionary.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/dictionary/document-type")
public class DocumentTypeAdminController {

    private final DocumentTypeService documentTypeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentTypeDTO create(@Valid @RequestBody DocumentTypeDTO typeDIO) {
        return documentTypeService.create(typeDIO);
    }

    @PutMapping("/{id}")
    public DocumentTypeDTO update(@PathVariable Long id, @Valid @RequestBody DocumentTypeDTO typeDIO) {
        return documentTypeService.update(id, typeDIO);
    }
}
