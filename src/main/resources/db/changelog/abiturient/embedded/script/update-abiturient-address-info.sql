UPDATE ab_address
SET ab_adr_country      = '-',
    ab_adr_country_in   = '-',
    ab_adr_region_in    = ab_adr_region,
    ab_adr_district_in    = ab_adr_district,
    ab_adr_city_in      = ab_adr_city,
    ab_adr_street_in    = ab_adr_street,
    ab_adr_house_in     = ab_adr_house,
    ab_adr_building_in  = ab_adr_building,
    ab_adr_apartment_in = ab_adr_apartment
WHERE ab_adr_country IS NULL
  AND ab_adr_country_in IS NULL
  AND ab_adr_region_in IS NULL
  AND ab_adr_district IS NULL
  AND ab_adr_city_in IS NULL
  AND ab_adr_street_in IS NULL
  AND ab_adr_house_in IS NULL
  AND ab_adr_building_in IS NULL
  AND ab_adr_apartment_in IS NULL