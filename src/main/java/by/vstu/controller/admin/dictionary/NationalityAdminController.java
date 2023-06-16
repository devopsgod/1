package by.vstu.controller.admin.dictionary;

import by.vstu.dto.dictionary.profile.NationalityDTO;
import by.vstu.service.dictionary.NationalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/dictionary/nationality")
public class NationalityAdminController {

    private final NationalityService nationalityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NationalityDTO create(@Valid @RequestBody NationalityDTO nationalityDIO) {
        return nationalityService.create(nationalityDIO);
    }

    @PutMapping("/{id}")
    public NationalityDTO update(@PathVariable Long id, @Valid @RequestBody NationalityDTO nationalityDIO) {
        return nationalityService.update(id, nationalityDIO);
    }
}
