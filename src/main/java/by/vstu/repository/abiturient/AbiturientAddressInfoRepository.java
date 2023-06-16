package by.vstu.repository.abiturient;

import by.vstu.model.abiturient.AbiturientAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbiturientAddressInfoRepository extends JpaRepository<AbiturientAddress, Long> {
}
