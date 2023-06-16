package by.vstu.service.abiturient;

import by.vstu.dto.abiturient.*;
import by.vstu.dto.dictionary.speciality.AbiturientCompetitionStatusDTO;
import by.vstu.dto.dictionary.speciality.CompetitionStatusType;
import by.vstu.dto.dictionary.speciality.SpecialitySumDTO;
import by.vstu.exception.EntityNotFoundException;
import by.vstu.model.abiturient.Abiturient;
import by.vstu.model.abiturient.AbiturientSpeciality;
import by.vstu.model.abiturient.CompetitionInfo;
import by.vstu.model.abiturient.Document;
import by.vstu.model.dictionary.competition.Speciality;
import by.vstu.model.generator.AbiturientIdGenerator;
import by.vstu.repository.abiturient.AbiturientRepository;
import by.vstu.service.dictionary.DocumentService;
import by.vstu.service.dictionary.SpecialityService;
import by.vstu.service.mapper.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AbiturientService {

    private final AbiturientRepository repository;

    private final AbiturientDTOMapper mapper;

    private final AbiturientProfileDTOMapper profileDTOMapper;

    private final AbiturientAddressInfoDTOMapper addressInfoDTOMapper;

    private final AbiturientEducationInfoDTOMapper educationInfoDTOMapper;

    private final AbiturientAdditionalInfoDTOMapper additionalInfoDTOMapper;

    private final DocumentService documentService;

    private final SpecialityService specialityService;

    @PostConstruct
    private void initAccountId() {
        AbiturientIdGenerator.setIdValue(repository.getMaxId() < 50000 ? 50000L : repository.getMaxId());
    }

    public Page<AbiturientDOO> findAllPageable(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public AbiturientDOO getAbiturient(Long id) {
        return mapper.toDTO(findByIdNotNull(id));
    }

    public List<Abiturient> getInternalAbiturientsWithSpecialities() {
        return repository.getInternalAbiturientsWithSpecialities();
    }

    public List<Abiturient> getInternalAbiturientsWithoutSpecialities() {
        return repository.getInternalAbiturientsWithoutSpecialities();
    }

    public Integer countInternalApprovedAbiturient() {
        return repository.countInternalApprovedAbiturient();
    }

    public AbiturientDOO createAccount(String email) {
        if (repository.isEmailExists(email)) {
            return mapper.toDTO(repository.findByEmail(email));
        }
        Abiturient abiturient = new Abiturient();
        abiturient.setEmail(email);
        abiturient = repository.save(abiturient);
        log.info("Abituient account with id=" + abiturient.getId() + " and email=" + email + " created");
        return mapper.toDTO(abiturient);
    }

    @Transactional
    public AbiturientDOO fillProfileInfo(Long abiturientId, AbiturientProfileDTO profileDIO) {
        Abiturient abiturient = findByIdNotNull(abiturientId);

        log.info("Abituient(" + abiturientId + ") personal info filled");

        abiturient.setAbiturientProfile(profileDTOMapper.toEntity(profileDIO, abiturient));
        abiturient.setPersonalInfoFilled(true);

        return mapper.toDTO(repository.save(abiturient));
    }

    @Transactional
    public AbiturientDOO fillAddressInfo(Long abiturientId, AbiturientAddressInfoDTO addressInfoDIO) {
        Abiturient abiturient = findByIdNotNull(abiturientId);

        log.info("Abituient(" + abiturientId + ") address info filled");

        abiturient.setAbiturientAddress(addressInfoDTOMapper.toEntity(addressInfoDIO, abiturient));
        abiturient.setAddressInfoFilled(true);

        return mapper.toDTO(repository.save(abiturient));
    }

    @Transactional
    public AbiturientDOO fillEducationInfo(Long abiturientId, AbiturientEducationInfoDTO educationInfoDIO) {
        Abiturient abiturient = findByIdNotNull(abiturientId);

        log.info("Abituient(" + abiturientId + ") education info filled");

        abiturient.setAbiturientEducationInfo(educationInfoDTOMapper.toEntity(educationInfoDIO, abiturient));
        abiturient.setEducationInfoFilled(true);

        return mapper.toDTO(repository.save(abiturient));
    }

    @Transactional
    public AbiturientDOO fillCompetitionInfo(Long abiturientId, AbiturientCompetitionInfoDTO competitionInfoDTO) {
        Abiturient abiturient = findByIdNotNull(abiturientId);

        log.info("Abituient(" + abiturientId + ") competition info filled");

        CompetitionInfo competitionInfo = new CompetitionInfo();
        competitionInfo.setDocuments(competitionInfoDTO.getDocuments() == null ? Collections.emptySet() : documentService.convertDocuments(abiturient, competitionInfoDTO.getDocuments()));
        competitionInfo.setSpecialities(specialityService.convertSpecialities(abiturient, competitionInfoDTO.getSpecialityIds()));

        abiturient.setCompetitionInfo(competitionInfo);
        abiturient.setCompetitionInfoFilled(true);
        return mapper.toDTO(repository.save(abiturient));
    }

    @Transactional
    public AbiturientDOO fillAdditionalInfo(Long abiturientId, AbiturientAdditionalInfoDTO additionalInfoDIO) {
        Abiturient abiturient = findByIdNotNull(abiturientId);

        log.info("Abituient(" + abiturientId + ") additional info filled");

        abiturient.setAbiturientAdditionalInfo(additionalInfoDTOMapper.toEntity(additionalInfoDIO, abiturient));
        abiturient.setAdditionalInfoFilled(true);

        return mapper.toDTO(repository.save(abiturient));
    }

    public AbiturientDOO approveAbiturient(Long abiturientId, ApproveStatus status) {
        Abiturient abiturient = findByIdNotNull(abiturientId);

        log.info("Abituient(" + abiturientId + ") was approved");
        abiturient.setProfileApproved(ApproveStatus.ACTIVE.equals(status));
        return mapper.toDTO(repository.save(abiturient));
    }

    @Transactional
    public AbiturientCompetitionStatusDTO getCompetitionStatus(Long abiturientId) {
        CompetitionInfo info = findByIdNotNull(abiturientId).getCompetitionInfo();

        boolean isDesigner = info.getSpecialities().stream().anyMatch(as -> as.getSpeciality().getName().startsWith("Дизайн"));
        double abiturientSum = 0;

        if (isDesigner) {
            long countDocuments = info.getDocuments().stream().filter(doc -> !doc.getEducationDocumentType().getInternal()).count();
            for (Document document : info.getDocuments()) {
                if (!document.getEducationDocumentType().getInternal()) {
                    abiturientSum += (double) document.getMarkHundred() / countDocuments;
                } else {
                    abiturientSum += document.getMarkHundred();
                }
            }
        } else {
            abiturientSum = info.getDocuments().stream().mapToLong(Document::getMarkHundred).sum();
        }

        AbiturientCompetitionStatusDTO competitonStatus = new AbiturientCompetitionStatusDTO();
        competitonStatus.setAbiturientId(abiturientId);
        competitonStatus.setSum(abiturientSum);

        List<Speciality> specialities = info.getSpecialities()
                .stream().map(AbiturientSpeciality::getSpeciality).collect(Collectors.toCollection(LinkedList::new));
        if (specialities.size() > 1) {
            List<SpecialitySumDTO> specSums = new LinkedList<>();
            for (Speciality speciality : specialities) {
                SpecialitySumDTO sumDTO = new SpecialitySumDTO();
                sumDTO.setSpeciality(speciality.getName().concat(speciality.typeToString()));
                sumDTO.setSum(speciality.getCurrentSum());
                sumDTO.setHalfBall(speciality.getHalfSum());
                sumDTO.setStatus(abiturientSum >= speciality.getCurrentSum() ?
                        isPrioritySet(specSums) ? CompetitionStatusType.PASS : CompetitionStatusType.PRIORITY_PASS
                        : CompetitionStatusType.NOT_PASS);
                specSums.add(sumDTO);
            }
            competitonStatus.setSpecialities(specSums);
        } else {
            Speciality speciality = specialities.get(0);
            SpecialitySumDTO specialitySum = new SpecialitySumDTO();
            specialitySum.setStatus(abiturientSum >= speciality.getCurrentSum() ?
                    CompetitionStatusType.PRIORITY_PASS : CompetitionStatusType.NOT_PASS);
            specialitySum.setSpeciality(speciality.getName().concat(speciality.typeToString()));
            specialitySum.setSum(speciality.getCurrentSum());
            specialitySum.setHalfBall(speciality.getHalfSum());
            competitonStatus.setSpecialities(Collections.singletonList(specialitySum));
        }
        return competitonStatus;
    }

    private boolean isPrioritySet(List<SpecialitySumDTO> specialities) {
        return specialities.stream().anyMatch(spec -> spec.getStatus().equals(CompetitionStatusType.PRIORITY_PASS));
    }

    public Abiturient findByIdNotNull(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Abiturient with id " + id + " not found"));
    }
}
