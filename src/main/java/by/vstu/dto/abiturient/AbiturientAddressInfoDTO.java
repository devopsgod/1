package by.vstu.dto.abiturient;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AbiturientAddressInfoDTO {

    @NotEmpty
    @Size(max = 6)
    private String postCode;

    @NotEmpty
    @Size(max = 6)
    private String postCodeIn;

    @NotEmpty
    @Size(max = 40)
    private String country;

    @NotEmpty
    @Size(max = 40)
    private String countryIn;

    @NotEmpty
    @Size(max = 100)
    private String region;

    @NotEmpty
    @Size(max = 100)
    private String regionIn;

    @NotEmpty
    @Size(max = 100)
    private String district;

    @NotEmpty
    @Size(max = 100)
    private String districtIn;

    @NotEmpty
    @Size(max = 100)
    private String city;

    @NotEmpty
    @Size(max = 100)
    private String cityIn;

    @NotNull
    @Size(min = 2, max = 100)
    private String street;

    @NotNull
    @Size(min = 2, max = 100)
    private String streetIn;

    @NotEmpty
    @Size(max = 5)
    private String house;

    @NotEmpty
    @Size(max = 5)
    private String houseIn;

    @Size(max = 5)
    private String building;

    @Size(max = 5)
    private String buildingIn;

    @Size(max = 5)
    private String apartment;

    @Size(max = 5)
    private String apartmentIn;

    @NotNull
    @Size(min = 2, max = 100)
    private String phone;
}
