UPDATE ab_profile
SET ab_p_first_name_en = '-',
    ab_p_last_name_en  = '-'
WHERE ab_p_first_name_en IS NULL
  AND ab_p_last_name_en IS NULL