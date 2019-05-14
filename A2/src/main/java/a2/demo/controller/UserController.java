package a2.demo.controller;

import a2.demo.model.User;
import a2.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/users")
public class UserController {

    @Autowired
    UserRepository usersRepository;

    @GetMapping
    public List<User> getAll() {
        return usersRepository.findAll();
    }

    @GetMapping("/login")
    public ModelAndView displayLoginPage()
    {
        ModelAndView modelAndView = new ModelAndView("loginPage");
        return modelAndView;
    }

    @GetMapping("/signUp")
    public ModelAndView displaySignUpPage(){
        ModelAndView modelAndView = new ModelAndView("signUpPage");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }

    @PostMapping("/signUp")
    public ModelAndView registrationProcess(User user)
    {
        usersRepository.save(user);
        return new ModelAndView("loginPage");
    }

    @PutMapping
    public User updateUser(@RequestBody User users) {
        users.setPassword("nouaparola");
        return usersRepository.save(users);
    }

    @DeleteMapping(value = "/{username}")
    public void deleteByUsername(@PathVariable String username) {
        usersRepository.deleteByUsername(username);
    }

}
