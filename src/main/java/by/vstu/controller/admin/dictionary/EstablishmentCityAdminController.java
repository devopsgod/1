package by.vstu.controller.admin.dictionary;

import by.vstu.dto.dictionary.education.EstablishmentCityDTO;
import by.vstu.service.dictionary.EstablishmentCityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/dictionary/establishment-city")
public class EstablishmentCityAdminController {

    private final EstablishmentCityService establishmentCityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstablishmentCityDTO create(@Valid @RequestBody EstablishmentCityDTO cityDIO) {
        return establishmentCityService.create(cityDIO);
    }

    @PutMapping("/{id}")
    public EstablishmentCityDTO update(@PathVariable Long id, @Valid @RequestBody EstablishmentCityDTO cityDIO) {
        return establishmentCityService.update(id, cityDIO);
    }
}
