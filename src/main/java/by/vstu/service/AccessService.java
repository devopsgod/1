package by.vstu.service;

import by.vstu.repository.abiturient.AbiturientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accessService")
public class AccessService {

    private final AbiturientRepository abiturientRepository;

    @Autowired
    public AccessService(AbiturientRepository abiturientRepository) {
        this.abiturientRepository = abiturientRepository;
    }

    public boolean onAbiturientProfileUpdate(Long abiturientId, String email) {
        return abiturientRepository.isUpdateEnabled(abiturientId, email);
    }

    public boolean onAbiturientStatusCompetition(Long abiturientId, String email) {
        return abiturientRepository.isCompetitionStatusEnabled(abiturientId, email);
    }
}
