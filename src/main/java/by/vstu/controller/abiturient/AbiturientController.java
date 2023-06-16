package by.vstu.controller.abiturient;

import by.vstu.dto.abiturient.*;
import by.vstu.dto.dictionary.speciality.AbiturientCompetitionStatusDTO;
import by.vstu.service.abiturient.AbiturientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/abiturient")
public class AbiturientController {

    private final AbiturientService abiturientService;

    @GetMapping
    @PreAuthorize("hasRole('ABITURIENT') && #oauth2.hasScope('ui-client') && #oauth2.hasScope('abiturient:write')")
    public AbiturientDOO registerAbiturient(Principal principal) {
        return abiturientService.createAccount(principal.getName());
    }

    @PutMapping(value = "/profile/{id}")
    @PreAuthorize("hasRole('ABITURIENT') && #oauth2.hasScope('ui-client') && #oauth2.hasScope('abiturient:write') && @accessService.onAbiturientProfileUpdate(#id, authentication.getName())")
    public AbiturientDOO createProfile(@PathVariable Long id, @Valid @RequestBody AbiturientProfileDTO profileDIO) {
        return abiturientService.fillProfileInfo(id, profileDIO);
    }

    @PutMapping(value = "/address/{id}")
    @PreAuthorize("hasRole('ABITURIENT') && #oauth2.hasScope('ui-client') && #oauth2.hasScope('abiturient:write') && @accessService.onAbiturientProfileUpdate(#id, authentication.getName())")
    public AbiturientDOO fillAddress(@PathVariable Long id, @Valid @RequestBody AbiturientAddressInfoDTO addressInfoDIO) {
        return abiturientService.fillAddressInfo(id, addressInfoDIO);
    }

    @PutMapping(value = "/education/{id}")
    @PreAuthorize("hasRole('ABITURIENT') && #oauth2.hasScope('ui-client') && #oauth2.hasScope('abiturient:write') && @accessService.onAbiturientProfileUpdate(#id, authentication.getName())")
    public AbiturientDOO fillEducation(@PathVariable Long id, @Valid @RequestBody AbiturientEducationInfoDTO educationInfoDIO) {
        return abiturientService.fillEducationInfo(id, educationInfoDIO);
    }

    @PutMapping(value = "/competition/{id}")
    @PreAuthorize("hasRole('ABITURIENT') && #oauth2.hasScope('ui-client') && #oauth2.hasScope('abiturient:write') && @accessService.onAbiturientProfileUpdate(#id, authentication.getName())")
    public AbiturientDOO fillCompetition(@PathVariable Long id, @Valid @RequestBody AbiturientCompetitionInfoDTO competitionInfo) {
        return abiturientService.fillCompetitionInfo(id, competitionInfo);
    }

    @PutMapping(value = "/addinfo/{id}")
    @PreAuthorize("hasRole('ABITURIENT') && #oauth2.hasScope('ui-client') && #oauth2.hasScope('abiturient:write') && @accessService.onAbiturientProfileUpdate(#id, authentication.getName())")
    public AbiturientDOO fillAddInfo(@PathVariable Long id, @Valid @RequestBody AbiturientAdditionalInfoDTO additionalInfoDIO) {
        return abiturientService.fillAdditionalInfo(id, additionalInfoDIO);
    }

    @GetMapping(value = "/{id}/status")
    @PreAuthorize("#oauth2.hasScope('ui-client') && #oauth2.hasScope('abiturient:read') && @accessService.onAbiturientStatusCompetition(#id, authentication.getName())")
    public AbiturientCompetitionStatusDTO getAbiturientCompetitionStatus(@PathVariable Long id) {
        return abiturientService.getCompetitionStatus(id);
    }
}
