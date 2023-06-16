package by.vstu.repository.abiturient;

import by.vstu.model.abiturient.AbiturientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbiturientProfileInfoRepository extends JpaRepository<AbiturientProfile, Long> {
}
