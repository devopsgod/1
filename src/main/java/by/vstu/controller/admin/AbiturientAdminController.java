package by.vstu.controller.admin;

import by.vstu.dto.abiturient.AbiturientDOO;
import by.vstu.dto.abiturient.ApproveStatus;
import by.vstu.service.abiturient.AbiturientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/abiturient")
public class AbiturientAdminController {

    private final AbiturientService abiturientService;

    @GetMapping
    @PreAuthorize("#oauth2.hasScope('abiturient:read') && #oauth2.hasScope('abiturient:admin')")
    public Page<AbiturientDOO> getAllPageable(Pageable pageable) {
        return abiturientService.findAllPageable(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("#oauth2.hasScope('abiturient:read') && #oauth2.hasScope('abiturient:admin')")
    public AbiturientDOO get(@PathVariable Long id) {
        return abiturientService.getAbiturient(id);
    }

    @PostMapping("/{id}/approve")
    @PreAuthorize("#oauth2.hasScope('abiturient:write') && #oauth2.hasScope('abiturient:admin')")
    public AbiturientDOO approveAbiturient(@PathVariable Long id, @RequestParam String status) {
        return abiturientService.approveAbiturient(id, ApproveStatus.valueOf(status.toUpperCase()));
    }
}
