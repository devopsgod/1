package by.vstu.controller.dictionary;

import by.vstu.dto.dictionary.education.LanguageDTO;
import by.vstu.service.dictionary.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/language")
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping("/contains")
    public Page<LanguageDTO> getLanguageByName(@RequestParam(value = "fragment") String fragment, Pageable pageable) {
        return languageService.findByNamePageable(fragment, pageable);
    }
}
