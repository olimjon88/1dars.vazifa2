package uz.pdp.vazifa2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.vazifa2.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    boolean existsByNameAndCategoryIdAndStatusTrue(String name, Integer category_id);

    boolean existsByNameAndCategoryIdAndStatusTrueAndIdNot(String name, Integer category_id, Integer id);

    List<Task> findAllByStatusTrue();

    List<Task> findAllByCategoryIdAndLanguageIdAndStatusTrue(Integer category_id, Integer language_id);

    Optional<Task> findByIdAndStatusTrue(Integer integer);
}
