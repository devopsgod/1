package by.vstu.controller.abiturient;

import by.vstu.dto.abiturient.AbiturientExportDTO;
import by.vstu.service.abiturient.AbiturientExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/abiturient/export")
public class AbiturientExportController {

    private final AbiturientExportService abiturientExportService;

    @GetMapping
    public List<AbiturientExportDTO> getAbiturients(
            @RequestParam(value = "documentTypeId") Long documentTypeId,
            @RequestParam(value = "seria", required = false) String seria,
            @RequestParam(value = "number") String number) {
        return abiturientExportService.getAbiturients(documentTypeId, seria, number);
    }

    @PostMapping
    public AbiturientExportDTO updateAbiturient(@Valid @RequestBody AbiturientExportDTO exportDIO) {
        return abiturientExportService.updateAbiturient(exportDIO);
    }
}
