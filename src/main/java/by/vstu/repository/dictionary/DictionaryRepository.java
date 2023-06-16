package by.vstu.repository.dictionary;

import by.vstu.model.dictionary.DictionaryNamedEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface DictionaryRepository<T extends DictionaryNamedEntity> extends JpaRepository<T, Long> {

    T findByName(String name);

    Page<T> findByNameIgnoreCaseContaining(String name, Pageable pageable);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM #{#entityName} t WHERE t.name = :name")
    boolean existsByName(@Param("name") String name);
}
