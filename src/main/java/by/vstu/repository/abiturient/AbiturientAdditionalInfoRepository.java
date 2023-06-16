package by.vstu.repository.abiturient;

import by.vstu.model.abiturient.AbiturientAdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbiturientAdditionalInfoRepository extends JpaRepository<AbiturientAdditionalInfo, Long> {
}
