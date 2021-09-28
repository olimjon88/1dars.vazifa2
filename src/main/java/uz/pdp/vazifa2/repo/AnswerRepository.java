package uz.pdp.vazifa2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.vazifa2.entity.Answer;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllByTaskId(Integer task_id);

    List<Answer> findAllByUserId(Integer user_id);

}
