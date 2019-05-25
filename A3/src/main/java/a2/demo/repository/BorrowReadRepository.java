package a2.demo.repository;

import a2.demo.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowReadRepository extends JpaRepository<Borrow,Integer> {
}
