package a2.demo.command;

import a2.demo.model.Staff;
import a2.demo.repository.StaffWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterStaffCommandHandler implements ICommandHandler {
    @Autowired
    StaffWriteRepository userRepository;

    @Override
    public void execute(ICommand command) {
        Staff staff = (Staff) command;
        userRepository.save(staff);
    }
}
