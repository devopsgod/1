package by.vstu.repository.abiturient;

import by.vstu.model.abiturient.AbiturientSpeciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbiturientSpecialityRepository extends JpaRepository<AbiturientSpeciality, Long> {

    void removeAllByAbiturientId(Long abiturientId);

    Long countBySpecialityIdAndAbiturientIdGreaterThan(Long specialityId, Long minValue);

    Long countByAbiturientProfileApprovedIsTrueAndSpecialityIdAndAbiturientIdGreaterThan(Long specialityId, Long minValue);
}
