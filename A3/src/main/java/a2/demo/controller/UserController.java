package a2.demo.controller;

import a2.demo.decorator.OwnershipBook;
import a2.demo.model.Book;
import a2.demo.model.Borrow;
import a2.demo.model.User;
import a2.demo.model.Waiting;
import a2.demo.repository.BookReadRepository;
import a2.demo.repository.BookWriteRepository;
import a2.demo.repository.BorrowReadRepository;
import a2.demo.repository.BorrowWriteRepository;
import a2.demo.repository.UserReadRepository;
import a2.demo.repository.UserWriteRepository;
import a2.demo.repository.WaitingReadRepository;
import a2.demo.repository.WaitingWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Observer;

@RestController
@RequestMapping(value = "/rest/users")
public class UserController {

    @Autowired
    UserWriteRepository usersRepository;

    @Autowired
    BookWriteRepository bookRepository;

    @Autowired
    BorrowWriteRepository borrowRepository;

    @Autowired
    WaitingWriteRepository waitingRepository;

    @Autowired
    UserReadRepository usersRepository1;

    @Autowired
    BookReadRepository bookRepository1;

    @Autowired
    BorrowReadRepository borrowRepository1;

    @Autowired
    WaitingReadRepository waitingRepository1;

    @GetMapping
    public List<User> getAll() {
        return usersRepository1.findAll();
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
        if(usersRepository1.existsById(user.getUsername())) {
            if(usersRepository1.findById(user.getUsername()).get().getPassword().equals(user.getPassword()) && usersRepository1.findById(user.getUsername()).get().getAccepted()==true)
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
        Book book = bookRepository1.findById(id).get();
        OwnershipBook ownershipBook = new OwnershipBook(book);
        if(ownershipBook.getAvailability()==1)
        {
            ownershipBook.changeAvailability();
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
        Book book = bookRepository1.findById(id).get();
        List<Waiting> waiting = waitingRepository1.findAll();
        List<Borrow> borrows = borrowRepository1.findAll();
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
                    OwnershipBook ownershipBook = new OwnershipBook(book);
                    ownershipBook.changeAvailability();
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
