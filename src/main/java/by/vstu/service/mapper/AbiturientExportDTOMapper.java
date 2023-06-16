package by.vstu.service.mapper;

import by.vstu.dto.abiturient.AbiturientExportDTO;
import by.vstu.model.abiturient.Abiturient;
import by.vstu.model.abiturient.AbiturientAdditionalInfo;
import by.vstu.model.abiturient.AbiturientAddress;
import by.vstu.model.abiturient.AbiturientEducationInfo;
import by.vstu.model.abiturient.AbiturientProfile;
import by.vstu.model.abiturient.CompetitionInfo;
import by.vstu.model.dictionary.education.EducationInstitution;
import by.vstu.repository.dictionary.EducationInstitutionRepository;
import by.vstu.service.dictionary.DocumentService;
import by.vstu.service.dictionary.DocumentTypeService;
import by.vstu.service.dictionary.EducationLevelService;
import by.vstu.service.dictionary.EducationTypeService;
import by.vstu.service.dictionary.EstablishmentCityService;
import by.vstu.service.dictionary.LanguageService;
import by.vstu.service.dictionary.NationalityService;
import by.vstu.service.dictionary.SpecialityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class AbiturientExportDTOMapper implements EntityToDTOMapper<AbiturientExportDTO, Abiturient, AbiturientExportDTO> {

    private final SpecialityDTOMapper specialityDTOMapper;
    private final DocumentDTOMapper documentDTOMapper;

    private final DocumentTypeService documentTypeService;
    private final NationalityService nationalityService;
    private final LanguageService languageService;
    private final EducationTypeService educationTypeService;
    private final EducationLevelService educationLevelService;
    private final EstablishmentCityService establishmentCityService;
    private final DocumentService documentService;
    private final SpecialityService specialityService;
    private final EducationInstitutionRepository educationInstitutionRepository;

    @Override
    public AbiturientExportDTO toDTO(Abiturient abiturient, Object... args) {
        AbiturientExportDTO doo = new AbiturientExportDTO();
        doo.setId(abiturient.getId());
        doo.setEmail(abiturient.getEmail());

        if (abiturient.getPersonalInfoFilled()) {
            AbiturientProfile profile = abiturient.getAbiturientProfile();
            doo.setIdDocType(profile.getDocumentType().getId());
            doo.setDocSeria(profile.getDocumentSeria());
            doo.setDocNumber(profile.getDocumentNumber());
            doo.setDocDate(profile.getDocumentDate());
            doo.setDocOrgan(profile.getDocumentOrgan());
            doo.setFirstName(profile.getFirstName());
            doo.setFirstNameEn(profile.getFirstNameEn());
            doo.setLastName(profile.getLastName());
            doo.setLastNameEn(profile.getLastNameEn());
            doo.setMiddleName(profile.getMiddleName());
            doo.setBirthDate(profile.getBirthDate());
            doo.setBirthPlace(profile.getBirthPlace());
            doo.setIdGragd(profile.getNationality().getId());
            doo.setIdentificationNumber(profile.getIdentificationNumber());
            doo.setSex(profile.getSex());
        }
        if (abiturient.getAddressInfoFilled()) {
            AbiturientAddress address = abiturient.getAbiturientAddress();
            doo.setAddressPoscode(address.getPostCode());
            doo.setAddressPoscodeIn(address.getPostCodeIn());
            doo.setAddressCountry(address.getCountry());
            doo.setAddressCountryIn(address.getCountryIn());
            doo.setAddressRegion(address.getRegion());
            doo.setAddressRegionIn(address.getRegionIn());
            doo.setAddressDistrict(address.getDistrict());
            doo.setAddressDistrictIn(address.getDistrictIn());
            doo.setAddressCity(address.getCity());
            doo.setAddressCityIn(address.getCityIn());
            doo.setAddressStreet(address.getStreet());
            doo.setAddressStreetIn(address.getStreetIn());
            doo.setAddressHouse(address.getHouse());
            doo.setAddressHouseIn(address.getHouseIn());
            doo.setAddressBuilding(address.getBuilding());
            doo.setAddressBuildingIn(address.getBuildingIn());
            doo.setAddressApartment(address.getApartment());
            doo.setAddressApartmentIn(address.getApartmentIn());
            doo.setAddressPhone(address.getPhone());
        }
        if (abiturient.getEducationInfoFilled()) {
            AbiturientEducationInfo educationInfo = abiturient.getAbiturientEducationInfo();
            doo.setIdEduLevel(educationInfo.getEducationLevel().getId());
            doo.setIdUoType(educationInfo.getEducationInstitution().getEducationType().getId());
            doo.setIdUoMesto(educationInfo.getEducationInstitution().getEstablishmentCity().getId());
            doo.setUoName(educationInfo.getEducationInstitution().getName());
            doo.setUoYear(educationInfo.getEndYear());
            doo.setIdLanguage(educationInfo.getLanguage().getId());
            doo.setGoldMedalist(educationInfo.getGoldMedalist());
            doo.setHonours(educationInfo.getHonours());
        }
        if (abiturient.getAdditionalInfoFilled()) {
            AbiturientAdditionalInfo additionalInfo = abiturient.getAbiturientAdditionalInfo();
            doo.setFatherFIO(additionalInfo.getFatherFIO());
            doo.setFatherWork(additionalInfo.getFatherWork());
            doo.setFatherPhone(additionalInfo.getFatherPhone());
            doo.setMotherFIO(additionalInfo.getMotherFIO());
            doo.setMotherWork(additionalInfo.getMotherWork());
            doo.setMotherPhone(additionalInfo.getMotherPhone());
            doo.setMinorCount(additionalInfo.getChildCount());
            doo.setWorkPlace(additionalInfo.getWorkPlace());
            doo.setExperience(additionalInfo.getExperience());
            doo.setReAdmission(additionalInfo.getReAdmission());
        }
        if (abiturient.getCompetitionInfoFilled()) {
            doo.setSpecialities(abiturient.getCompetitionInfo().getSpecialities()
                    .stream().map(sp -> specialityDTOMapper.toDTO(sp.getSpeciality())).collect(Collectors.toCollection(LinkedHashSet::new)));
            doo.setDocuments(CollectionUtils.isEmpty(abiturient.getCompetitionInfo().getDocuments()) ?
                    Collections.emptySet() : abiturient.getCompetitionInfo().getDocuments()
                    .stream().map(documentDTOMapper::toDTO).collect(Collectors.toSet()));
        }

        return doo;
    }

    @Override
    public Abiturient toEntity(AbiturientExportDTO dto, Object... args) {
        Abiturient abiturient = (Abiturient) args[0];

        log.info("Updating personal information of abiturient with id=" + dto.getId() + "...");
        AbiturientProfile profile = Optional.ofNullable(abiturient.getAbiturientProfile()).orElse(new AbiturientProfile());
        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setMiddleName(dto.getMiddleName());
        profile.setFirstNameEn(dto.getFirstNameEn());
        profile.setLastNameEn(dto.getLastNameEn());
        profile.setDocumentType(documentTypeService.findByIdNotNull(dto.getIdDocType()));
        profile.setDocumentSeria(dto.getDocSeria());
        profile.setDocumentNumber(dto.getDocNumber());
        profile.setDocumentDate(dto.getDocDate());
        profile.setDocumentOrgan(dto.getDocOrgan());
        profile.setBirthDate(dto.getBirthDate());
        profile.setBirthPlace(dto.getBirthPlace());
        profile.setIdentificationNumber(dto.getIdentificationNumber());
        profile.setNationality(nationalityService.findByIdNotNull(dto.getIdGragd()));
        profile.setSex(dto.getSex());
        abiturient.setAbiturientProfile(profile);
        abiturient.setPersonalInfoFilled(true);
        log.info("Done.");

        log.info("Updating address information of abiturient with id=" + dto.getId() + "...");
        AbiturientAddress abiturientAddress = Optional.ofNullable(abiturient.getAbiturientAddress()).orElse(new AbiturientAddress());
        abiturientAddress.setPostCode(dto.getAddressPoscode());
        abiturientAddress.setPostCodeIn(dto.getAddressPoscodeIn());
        abiturientAddress.setCountry(dto.getAddressCountry());
        abiturientAddress.setCountryIn(dto.getAddressCountryIn());
        abiturientAddress.setRegion(dto.getAddressRegion());
        abiturientAddress.setRegionIn(dto.getAddressRegionIn());
        abiturientAddress.setDistrict(dto.getAddressDistrict());
        abiturientAddress.setDistrictIn(dto.getAddressDistrictIn());
        abiturientAddress.setCity(dto.getAddressCity());
        abiturientAddress.setCityIn(dto.getAddressCityIn());
        abiturientAddress.setStreet(dto.getAddressStreet());
        abiturientAddress.setStreetIn(dto.getAddressStreetIn());
        abiturientAddress.setHouse(dto.getAddressHouse());
        abiturientAddress.setHouseIn(dto.getAddressHouseIn());
        abiturientAddress.setBuilding(dto.getAddressBuilding());
        abiturientAddress.setBuildingIn(dto.getAddressBuildingIn());
        abiturientAddress.setApartment(dto.getAddressApartment());
        abiturientAddress.setApartmentIn(dto.getAddressApartmentIn());
        abiturientAddress.setPhone(dto.getAddressPhone());
        abiturient.setAbiturientAddress(abiturientAddress);
        abiturient.setAddressInfoFilled(true);
        log.info("Done.");

        log.info("Updating information about education of abiturient with id=" + dto.getId() + "...");
        AbiturientEducationInfo abiturientEducationInfo = Optional.ofNullable(abiturient.getAbiturientEducationInfo()).orElse(new AbiturientEducationInfo());
        abiturientEducationInfo.setEducationLevel(educationLevelService.findByIdNotNull(dto.getIdEduLevel()));
        EducationInstitution educationInstitution = educationInstitutionRepository.findByNameAndEducationType_IdAndEstablishmentCity_Id(dto.getUoName(), dto.getIdUoType(), dto.getIdUoMesto());
        if (educationInstitution == null) {
            educationInstitution = new EducationInstitution(dto.getUoName());
            educationInstitution.setTemp(false);
            educationInstitution.setEstablishmentCity(establishmentCityService.findByIdNotNull(dto.getIdUoMesto()));
            educationInstitution.setEducationType(educationTypeService.findByIdNotNull(dto.getIdUoType()));
            educationInstitution = educationInstitutionRepository.save(educationInstitution);
        }
        abiturientEducationInfo.setEducationInstitution(educationInstitution);
        abiturientEducationInfo.setEndYear(dto.getUoYear());
        abiturientEducationInfo.setLanguage(languageService.findByIdNotNull(dto.getIdLanguage()));
        abiturientEducationInfo.setGoldMedalist(dto.getGoldMedalist());
        abiturientEducationInfo.setHonours(dto.getHonours());
        abiturient.setAbiturientEducationInfo(abiturientEducationInfo);
        abiturient.setEducationInfoFilled(true);
        log.info("Done.");

        log.info("Updating competition info of abiturient with id=" + dto.getId() + "...");
        CompetitionInfo competitionInfo = new CompetitionInfo();
        competitionInfo.setDocuments(documentService.convertDocuments(abiturient, dto.getDocuments()));
        competitionInfo.setSpecialities(specialityService.convertSpecialities(abiturient, dto.getSpecialityIds()));
        abiturient.setCompetitionInfo(competitionInfo);
        abiturient.setCompetitionInfoFilled(true);
        log.info("Done.");

        log.info("Updating additional about education of abiturient with id=" + dto.getId() + "...");
        AbiturientAdditionalInfo abiturientAdditionalInfo = Optional.ofNullable(abiturient.getAbiturientAdditionalInfo()).orElse(new AbiturientAdditionalInfo());
        abiturientAdditionalInfo.setFatherFIO(dto.getFatherFIO());
        abiturientAdditionalInfo.setFatherPhone(dto.getFatherPhone());
        abiturientAdditionalInfo.setFatherWork(dto.getFatherWork());
        abiturientAdditionalInfo.setMotherFIO(dto.getMotherFIO());
        abiturientAdditionalInfo.setMotherPhone(dto.getMotherPhone());
        abiturientAdditionalInfo.setMotherWork(dto.getMotherWork());
        abiturientAdditionalInfo.setChildCount(dto.getMinorCount());
        abiturientAdditionalInfo.setWorkPlace(dto.getWorkPlace());
        abiturientAdditionalInfo.setExperience(dto.getExperience());
        abiturientAdditionalInfo.setReAdmission(dto.getReAdmission());
        abiturient.setAbiturientAdditionalInfo(abiturientAdditionalInfo);
        abiturient.setAdditionalInfoFilled(true);
        log.info("Done.");

        return abiturient;
    }
}
