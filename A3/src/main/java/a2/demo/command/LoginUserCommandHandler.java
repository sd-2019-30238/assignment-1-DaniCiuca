package a2.demo.command;

import a2.demo.model.User;
import a2.demo.repository.UserWriteRepository;
import a2.demo.repository.UserReadRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginUserCommandHandler implements ICommandHandler {
    @Autowired
    UserWriteRepository userRepository;

    @Autowired
    UserReadRepository userRepository1;

    @Override
    public void execute(ICommand command) {
        User user = (User)command;
        if(userRepository1.existsById(user.getUsername())) {
            if (userRepository1.findById(user.getUsername()).get().getPassword().equals(user.getPassword()) && userRepository1.findById(user.getUsername()).get().getAccepted() == true)
                userRepository.save(user);
        }
    }
}
