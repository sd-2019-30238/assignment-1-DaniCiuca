package a2.demo.controller;

import a2.demo.model.Book;
import a2.demo.model.Borrow;
import a2.demo.model.User;
import a2.demo.model.Waiting;
import a2.demo.repository.BookRepository;
import a2.demo.repository.BorrowRepository;
import a2.demo.repository.UserRepository;
import a2.demo.repository.WaitingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Observer;

@RestController
@RequestMapping(value = "/rest/users")
public class UserController {

    @Autowired
    UserRepository usersRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    WaitingRepository waitingRepository;

    @GetMapping
    public List<User> getAll() {
        return usersRepository.findAll();
    }

    @GetMapping("/login")
    public ModelAndView displayLoginPage()
    {
        ModelAndView modelAndView = new ModelAndView("loginUser");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView verifyUser(User user)
    {
        ModelAndView modelAndView = new ModelAndView("loginUser");
        if(usersRepository.existsById(user.getUsername())) {
            if(usersRepository.findById(user.getUsername()).get().getPassword().equals(user.getPassword()) && usersRepository.findById(user.getUsername()).get().getAccepted()==true)
                return new ModelAndView("redirect:/rest/users/home/"+user.getUsername());
        }
        return modelAndView;
    }

    @GetMapping(value = "/home/{username}")
    public ModelAndView displayHomePage(@PathVariable String username)
    {
        ModelAndView modelAndView = new ModelAndView("userHome");
        User u = new User();
        u.setUsername(username);
        modelAndView.addObject("user",u);
        return modelAndView;
    }

    @PostMapping(value = "/home/borrow/{username}/{id}")
    public ModelAndView borrowBook(@PathVariable String username, @PathVariable int id)
    {
        Book book = bookRepository.findById(id).get();
        if(book.getAvailable()==1)
        {
            book.setAvailable(0);
            bookRepository.save(book);
            Borrow b =new Borrow();
            b.setBookID(book.getId());
            b.setUsername(username);
            borrowRepository.save(b);
        }
        else
        {
            Waiting w = new Waiting();
            w.setBookID(book.getId());
            w.setUsername(username);
            waitingRepository.save(w);
        }
        return new ModelAndView("redirect:/rest/users/home/"+username);
    }

    @PostMapping(value = "/home/return/{username}/{id}")
    public ModelAndView returnBook(@PathVariable String username, @PathVariable int id)
    {
        Book book = bookRepository.findById(id).get();
        List<Waiting> waiting = waitingRepository.findAll();
        List<Borrow> borrows = borrowRepository.findAll();
        for(Borrow a : borrows) {
            if (book.getId() == a.getBookID() && a.getUsername().equals(username)) {
                bookRepository.deleteById(a.getId());
                int ok = 0;
                for (Waiting w : waiting) {
                    if (w.getBookID() == book.getId()) {
                        waitingRepository.deleteById(w.getId());
                        Borrow b = new Borrow();
                        b.setBookID(book.getId());
                        b.setUsername(w.getUsername());
                        borrowRepository.save(b);
                        ok = 1;
                        break;
                    }
                }
                if (ok == 0) {
                    book.setAvailable(1);
                    bookRepository.save(book);
                }
            }
        }
        return new ModelAndView("redirect:/rest/users/home/"+username);
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
        return new ModelAndView("loginUser");
    }

    @PutMapping
    public User updateUser(@RequestBody User users) {
        users.setPassword("nouaparola");
        return usersRepository.save(users);
    }

    /*@DeleteMapping(value = "/{username}")
    public void deleteByUsername(@PathVariable String username) {
        usersRepository.deleteByUsername(username);
    }*/

}
