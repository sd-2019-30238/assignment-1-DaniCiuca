package a2.demo.repository;

import a2.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookWriteRepository extends JpaRepository<Book, Integer> {
}
