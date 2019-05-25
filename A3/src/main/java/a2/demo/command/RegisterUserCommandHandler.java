package a2.demo.command;

import a2.demo.model.User;
import a2.demo.repository.UserWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterUserCommandHandler implements ICommandHandler {

    @Autowired
    UserWriteRepository userRepository;


    @Override
    public void execute(ICommand command) {
        User user = (User)command;
        userRepository.save(user);
    }
}
