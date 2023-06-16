package by.vstu.controller.admin.dictionary;

import by.vstu.dto.dictionary.education.LanguageDTO;
import by.vstu.service.dictionary.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/dictionary/language")
public class LanguageAdminController {

    private final LanguageService languageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LanguageDTO create(@Valid @RequestBody LanguageDTO languageDIO) {
        return languageService.create(languageDIO);
    }

    @PutMapping("/{id}")
    public LanguageDTO update(@PathVariable Long id, @Valid @RequestBody LanguageDTO languageDIO) {
        return languageService.update(id, languageDIO);
    }
}
