package a2.demo.command;

import a2.demo.model.User;
import a2.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginUserCommandHandler implements ICommandHandler {
    @Autowired
    UserRepository userRepository;

    @Override
    public void execute(ICommand command) {
        User user = (User)command;
        if(userRepository.existsById(user.getUsername())) {
            if (userRepository.findById(user.getUsername()).get().getPassword().equals(user.getPassword()) && userRepository.findById(user.getUsername()).get().getAccepted() == true)
                userRepository.save(user);
        }
    }
}
