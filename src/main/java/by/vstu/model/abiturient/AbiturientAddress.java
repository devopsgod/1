package by.vstu.model.abiturient;

import by.vstu.model.PersistentEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "ab_address")
@AttributeOverride(name = "id", column = @Column(name = "ab_adr_id"))
public class AbiturientAddress extends PersistentEntity {

    @Column(name = "ab_adr_post_code", length = 6, nullable = false)
    private String postCode;

    @Column(name = "ab_adr_post_code_in", length = 6, nullable = false)
    private String postCodeIn;

    @Column(name = "ab_adr_country", length = 40, nullable = false)
    private String country;

    @Column(name = "ab_adr_country_in", length = 40, nullable = false)
    private String countryIn;

    @Column(name = "ab_adr_region", length = 100, nullable = false)
    private String region;

    @Column(name = "ab_adr_region_in", length = 100, nullable = false)
    private String regionIn;

    @Column(name = "ab_adr_district", length = 100, nullable = false)
    private String district;

    @Column(name = "ab_adr_district_in", length = 100, nullable = false)
    private String districtIn;

    @Column(name = "ab_adr_city", length = 100, nullable = false)
    private String city;

    @Column(name = "ab_adr_city_in", length = 100, nullable = false)
    private String cityIn;

    @Column(name = "ab_adr_street", length = 100, nullable = false)
    private String street;

    @Column(name = "ab_adr_street_in", length = 100, nullable = false)
    private String streetIn;

    @Column(name = "ab_adr_house", length = 5, nullable = false)
    private String house;

    @Column(name = "ab_adr_house_in", length = 5, nullable = false)
    private String houseIn;

    @Column(name = "ab_adr_building", length = 5)
    private String building;

    @Column(name = "ab_adr_building_in", length = 5)
    private String buildingIn;

    @Column(name = "ab_adr_apartment", length = 5)
    private String apartment;

    @Column(name = "ab_adr_apartment_in", length = 5)
    private String apartmentIn;

    @Column(name = "ab_adr_phone", length = 40, nullable = false)
    private String phone;
}
