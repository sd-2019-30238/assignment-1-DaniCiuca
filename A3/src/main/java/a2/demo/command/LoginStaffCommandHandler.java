package a2.demo.command;

import a2.demo.model.Staff;
import a2.demo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginStaffCommandHandler implements ICommandHandler {
    @Autowired
    StaffRepository staffRepository;

    @Override
    public void execute(ICommand command) {
        Staff staff = (Staff) command;
        if(staffRepository.existsById(staff.getUsername())) {
            if (staffRepository.findById(staff.getUsername()).get().getPassword().equals(staff.getPassword()))
                staffRepository.save(staff);
        }
    }
}
