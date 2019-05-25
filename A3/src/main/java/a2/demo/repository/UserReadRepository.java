package a2.demo.repository;

import a2.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReadRepository extends JpaRepository<User,String> {
}
