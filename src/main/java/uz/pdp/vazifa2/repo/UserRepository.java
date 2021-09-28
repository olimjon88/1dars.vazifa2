package uz.pdp.vazifa2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.vazifa2.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Integer id);

}
