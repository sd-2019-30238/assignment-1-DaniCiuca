package a2.demo.repository;

import a2.demo.model.Waiting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingWriteRepository extends JpaRepository<Waiting, Integer> {
}
