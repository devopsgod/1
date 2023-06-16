package by.vstu.service.abiturient;

import by.vstu.dto.abiturient.AbiturientExportDTO;
import by.vstu.exception.BusinessException;
import by.vstu.feign.client.AuthorizationServiceClient;
import by.vstu.feign.dto.CreateUserAccountDTO;
import by.vstu.feign.dto.TokenResponse;
import by.vstu.model.abiturient.Abiturient;
import by.vstu.repository.abiturient.AbiturientRepository;
import by.vstu.service.mapper.AbiturientExportDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbiturientExportService {

    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    private final AbiturientExportDTOMapper mapper;

    private final AbiturientRepository abiturientRepository;

    private final AuthorizationServiceClient authorizationClient;

    public List<AbiturientExportDTO> getAbiturients(Long documentTypeId, String seria, String number) {
        return mapper.toDTOs(abiturientRepository.getAbiturientsByDocument(documentTypeId, seria, number));
    }

    @Transactional
    public AbiturientExportDTO updateAbiturient(AbiturientExportDTO exportDIO) {
        Abiturient abiturient = abiturientRepository.findById(exportDIO.getId()).orElse(null);
        abiturient = (abiturient == null) ? initAbiturient(exportDIO) : abiturient;
        mapper.toEntity(exportDIO, abiturient);

        Abiturient saved = abiturientRepository.save(abiturient);
        log.info("Abiturient with id=" + saved.getId() + " is updated");
        return mapper.toDTO(saved);
    }

    private Abiturient initAbiturient(AbiturientExportDTO exportDIO) {
        Abiturient abiturient = new Abiturient();
        abiturient.setId(exportDIO.getId());
        if (abiturientRepository.isEmailExists(exportDIO.getEmail())) {
            throw new BusinessException("User's email should be unique. Please re-check it");
        }
        if (exportDIO.getEmail().matches(EMAIL_REGEX)) {
            log.info("Creating new abiturient with email: " + exportDIO.getEmail());
            createAuthUserAccount(exportDIO.getEmail());
            abiturient.setEmail(exportDIO.getEmail());
        } else {
            abiturient.setEmail(exportDIO.getId() + "@empty.vstu.by");
        }
        log.info("Creating new abiturient...");
        return abiturientRepository.save(abiturient);
    }

    private void createAuthUserAccount(String email) {
        TokenResponse response = authorizationClient.getOAuthClientToken();
        authorizationClient.createUserAccount("Bearer ".concat(response.getAccess_token()),
                new CreateUserAccountDTO(email));
    }
}
