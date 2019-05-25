package a2.demo.repository;

import a2.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReadRepository extends JpaRepository<Book, Integer> {
}
