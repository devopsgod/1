package by.vstu.repository.abiturient;

import by.vstu.model.abiturient.Abiturient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AbiturientRepository extends JpaRepository<Abiturient, Long> {

    @Query("SELECT coalesce(max(ab.id), 0) FROM Abiturient ab")
    Long getMaxId();

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Abiturient a WHERE a.email = :email")
    boolean isEmailExists(@Param("email") String email);

    Abiturient findByEmail(String email);

    @Query("SELECT a from Abiturient a " +
            "WHERE a.abiturientProfile.documentType.id = :docId AND a.abiturientProfile.documentSeria = :docSeria " +
            "AND a.abiturientProfile.documentNumber = :docNumber")
    List<Abiturient> getAbiturientsByDocument(@Param("docId") Long docId, @Param("docSeria") String docSeria, @Param("docNumber") String docNumber);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Abiturient a " +
            "WHERE a.email = :email AND a.id = :abiturientId AND a.profileApproved = false")
    boolean isUpdateEnabled(@Param("abiturientId") Long abiturientId, @Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Abiturient a " +
            "WHERE a.email = :email AND a.id = :abiturientId AND a.profileApproved = true " +
            "AND a.competitionInfoFilled = true")
    boolean isCompetitionStatusEnabled(@Param("abiturientId") Long abiturientId, @Param("email") String email);

    @Query("FROM Abiturient a where a.id >= 50000 AND a.competitionInfoFilled = TRUE AND a.competitionInfo.specialities IS NOT EMPTY")
    List<Abiturient> getInternalAbiturientsWithSpecialities();

    @Query("FROM Abiturient a where a.id >= 50000 AND a.competitionInfoFilled = FALSE AND a.competitionInfo.specialities IS EMPTY")
    List<Abiturient> getInternalAbiturientsWithoutSpecialities();

    @Query("SELECT count(a) FROM Abiturient a WHERE a.profileApproved = TRUE AND a.id >=50000")
    Integer countInternalApprovedAbiturient();
}
