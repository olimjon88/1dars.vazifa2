package uz.pdp.vazifa2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.vazifa2.entity.Example;

import java.util.List;
import java.util.Optional;

public interface ExampleRepository extends JpaRepository<Example, Integer> {
    boolean existsByTextAndTaskIdAndStatusTrue(String text, Integer task_id);

    boolean existsByTextAndTaskIdAndStatusTrueAndIdNot(String text, Integer task_id, Integer id);

    List<Example> findAllByTaskIdAndStatusTrue(Integer task_id);

    Optional<Example> findByIdAndStatusTrue(Integer integer);
}
