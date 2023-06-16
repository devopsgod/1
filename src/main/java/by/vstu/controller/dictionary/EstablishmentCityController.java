package by.vstu.controller.dictionary;

import by.vstu.dto.dictionary.education.EstablishmentCityDTO;
import by.vstu.service.dictionary.EstablishmentCityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/establishment-city")
public class EstablishmentCityController {

    private final EstablishmentCityService establishmentCityService;

    @GetMapping(value = "/contains")
    public Page<EstablishmentCityDTO> getEstablishmentCityByName(@RequestParam(value = "fragment") String fragment, Pageable pageable) {
        return establishmentCityService.findByNamePageable(fragment, pageable);
    }
}
