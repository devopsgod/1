package by.vstu.controller.dictionary;

import by.vstu.dto.dictionary.document.EducationDocumentTypeDTO;
import by.vstu.service.dictionary.EducationDocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/education-document-type")
public class EducationDocumentTypeController {

    private final EducationDocumentTypeService educationDocumentTypeService;

    @GetMapping(value = "/contains")
    public Page<EducationDocumentTypeDTO> getEducationDocumentTypeByName(@RequestParam(value = "fragment") String fragment, Pageable pageable) {
        return educationDocumentTypeService.findByNamePageable(fragment, pageable);
    }
}
