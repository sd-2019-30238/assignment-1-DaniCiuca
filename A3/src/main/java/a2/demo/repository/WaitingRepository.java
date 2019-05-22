package a2.demo.repository;

import a2.demo.model.Waiting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingRepository extends JpaRepository<Waiting, Integer> {
}
