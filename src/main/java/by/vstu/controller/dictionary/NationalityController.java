package by.vstu.controller.dictionary;

import by.vstu.dto.dictionary.profile.NationalityDTO;
import by.vstu.service.dictionary.NationalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/nationality")
public class NationalityController {

    private final NationalityService nationalityService;

    @GetMapping("/contains")
    public Page<NationalityDTO> getNationalityByName(@RequestParam(value = "fragment") String fragment, Pageable pageable) {
        return nationalityService.findByNamePageable(fragment, pageable);
    }
}
