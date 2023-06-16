package by.vstu.controller.dictionary;

import by.vstu.dto.dictionary.speciality.SpecialityDTO;
import by.vstu.dto.dictionary.speciality.SpecialitySearchDIO;
import by.vstu.service.dictionary.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/speciality")
public class SpecialityController {

    private final SpecialityService specialityService;

    @PostMapping
    public List<SpecialityDTO> getSpecialitiesBySearch(@Valid @RequestBody SpecialitySearchDIO searchDIO) {
        return specialityService.getSpecialitiesBySearch(searchDIO);
    }
}
