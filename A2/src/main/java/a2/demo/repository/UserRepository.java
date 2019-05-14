package a2.demo.repository;

import a2.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User,String> {
    @Modifying
    @Transactional
    @Query("delete from users u where u.username = ?1")
    void deleteByUsername(String username);
}
