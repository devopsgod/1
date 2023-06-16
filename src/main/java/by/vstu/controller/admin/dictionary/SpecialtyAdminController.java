package by.vstu.controller.admin.dictionary;

import by.vstu.dto.dictionary.speciality.SpecialityDTO;
import by.vstu.dto.dictionary.speciality.SpecialitySumDTO;
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
@RequestMapping(value = "/admin/dictionary/speciality")
public class SpecialtyAdminController {

    private final SpecialityService specialityService;

    @PostMapping("/synchronize")
    public List<SpecialityDTO> synchronizeSpecialitySum(@RequestBody @Valid List<SpecialitySumDTO> sum) {
        return specialityService.updateSpecialitySum(sum);
    }
}

