package a2.demo.repository;

import a2.demo.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffWriteRepository extends JpaRepository<Staff,String> {

}
