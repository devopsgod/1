package by.vstu.controller.dictionary;

import by.vstu.dto.dictionary.profile.DocumentTypeDTO;
import by.vstu.service.dictionary.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/document-type")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    @GetMapping(value = "/contains")
    public Page<DocumentTypeDTO> getDocumentTypeByName(@RequestParam(value = "fragment") String fragment, Pageable pageable) {
        return documentTypeService.findByNamePageable(fragment, pageable);
    }
}
