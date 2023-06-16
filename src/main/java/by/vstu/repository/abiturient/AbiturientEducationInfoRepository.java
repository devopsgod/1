package by.vstu.repository.abiturient;

import by.vstu.model.abiturient.AbiturientEducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbiturientEducationInfoRepository extends JpaRepository<AbiturientEducationInfo, Long> {
}
