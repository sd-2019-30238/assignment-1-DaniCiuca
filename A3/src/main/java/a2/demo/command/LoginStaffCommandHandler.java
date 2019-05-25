package a2.demo.command;

import a2.demo.model.Staff;
import a2.demo.repository.StaffReadRepository;
import a2.demo.repository.StaffWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginStaffCommandHandler implements ICommandHandler {
    @Autowired
    StaffWriteRepository staffRepository;

    @Autowired
    StaffReadRepository staffRepository1;

    @Override
    public void execute(ICommand command) {
        Staff staff = (Staff) command;
        if(staffRepository1.existsById(staff.getUsername())) {
            if (staffRepository1.findById(staff.getUsername()).get().getPassword().equals(staff.getPassword()))
                staffRepository.save(staff);
        }
    }
}
