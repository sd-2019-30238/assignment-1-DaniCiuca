package a2.demo.controller;

import a2.demo.model.Staff;
import a2.demo.model.User;
import a2.demo.repository.StaffRepository;
import a2.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/staff")
public class StaffController {
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<Staff> getAll() {
        return staffRepository.findAll();
    }

    @PostMapping
    public List<Staff> persist(@RequestBody final Staff staff) {
        staffRepository.save(staff);
        return staffRepository.findAll();
    }

    @GetMapping(value = "/{username}")
    public Staff findStaff(@PathVariable String username)
    {
        return staffRepository.findById(username).get();
    }

    @DeleteMapping(value = "/{username}")
    public void deleteByUsername(@PathVariable String username) {
        staffRepository.deleteById(username);
    }

    @PutMapping(value = "/{username}")
    public void acceptUser(@PathVariable String username)
    {
        User user = userRepository.findById(username).get();
        user.setAccepted(true);
        userRepository.save(user);
    }
}
