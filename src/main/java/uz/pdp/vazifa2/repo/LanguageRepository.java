package uz.pdp.vazifa2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.vazifa2.entity.Language;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    boolean existsByNameAndStatusTrue(String name);

    boolean existsByNameAndIdNotAndStatusTrue(String name, Integer id);

    List<Language> findAllByStatusTrue();

    Optional<Language> findByIdAndStatusTrue(Integer integer);
}
