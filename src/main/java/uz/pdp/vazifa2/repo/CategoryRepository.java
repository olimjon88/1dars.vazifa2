package uz.pdp.vazifa2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.vazifa2.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    boolean existsByNameAndStatusTrue(String name);

    boolean existsByNameAndIdNotAndStatusTrue(String name, Integer id);

    List<Category> findAllByStatusTrue();

    Optional<Category> findByIdAndStatusTrue(Integer integer);

    @Query(nativeQuery = true, value = "select *\n" +
            "from category t\n" +
            "         join category_language cl on t.id = cl.category_id\n" +
            "where language_id=:languageId and t.status = true")
    List<Category> findAllByLanguage(Integer languageId);
}
