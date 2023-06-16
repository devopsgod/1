package by.vstu.dto.abiturient;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public abstract class AbiturientExportObject {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String firstNameEn;

    @NotEmpty
    private String lastNameEn;

    private String middleName;

    @NotNull
    private Long idDocType;

    @NotEmpty
    private String docSeria;

    @NotNull
    private String docNumber;

    @NotNull
    private Date docDate;

    private String docOrgan;

    @NotNull
    private Long idGragd;

    private String identificationNumber;

    @NotNull
    private Byte sex;

    @NotNull
    private Date birthDate;

    @NotEmpty
    private String birthPlace;

    @NotEmpty
    @Size(max = 6)
    private String addressPoscode;

    @NotEmpty
    @Size(max = 6)
    private String addressPoscodeIn;

    @NotEmpty
    @Size(max = 40)
    private String addressCountry;

    @NotEmpty
    @Size(max = 40)
    private String addressCountryIn;
    @NotEmpty
    @Size(max = 100)
    private String addressRegion;

    @NotEmpty
    @Size(max = 100)
    private String addressRegionIn;

    @NotEmpty
    @Size(max = 100)
    private String addressDistrict;

    @NotEmpty
    @Size(max = 100)
    private String addressDistrictIn;

    @NotEmpty
    @Size(max = 100)
    private String addressCity;

    @NotEmpty
    @Size(max = 100)
    private String addressCityIn;

    @NotEmpty
    @Size(max = 100)
    private String addressStreet;

    @NotEmpty
    @Size(max = 100)
    private String addressStreetIn;

    @NotEmpty
    @Size(max = 5)
    private String addressHouse;

    @NotEmpty
    @Size(max = 5)
    private String addressHouseIn;

    @Size(max = 5)
    private String addressBuilding;

    @Size(max = 5)
    private String addressBuildingIn;

    @Size(max = 5)
    private String addressApartment;

    @Size(max = 5)
    private String addressApartmentIn;

    @NotEmpty
    private String addressPhone;

    @NotEmpty
    private String uoName;

    @NotNull
    private Long idUoType;

    @NotNull
    private Integer uoYear;

    @NotNull
    private Long idEduLevel;

    @NotNull
    private Long idUoMesto;

    @NotNull
    private Long idLanguage;

    @NotNull
    private Boolean goldMedalist;

    @NotNull
    private Boolean honours;

    @Size(max = 150)
    private String fatherFIO;

    @Size(max = 150)
    private String fatherWork;

    @Size(max = 50)
    private String fatherPhone;

    @Size(max = 150)
    private String motherFIO;

    @Size(max = 150)
    private String motherWork;

    @Size(max = 50)
    private String motherPhone;

    @Max(20)
    private Byte minorCount;

    @Size(max = 100)
    private String workPlace;

    @Max(35)
    private Byte experience;

    private Boolean reAdmission;
}
