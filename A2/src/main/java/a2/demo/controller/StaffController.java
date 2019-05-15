package a2.demo.controller;

import a2.demo.model.Book;
import a2.demo.model.Staff;
import a2.demo.model.User;
import a2.demo.repository.BookRepository;
import a2.demo.repository.StaffRepository;
import a2.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/staff")
public class StaffController {
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/login")
    public ModelAndView displayLoginPage()
    {
        ModelAndView modelAndView = new ModelAndView("loginStaff");
        modelAndView.addObject("staff",new Staff());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView verifyStaff(Staff staff)
    {
        ModelAndView modelAndView = new ModelAndView("loginStaff");
        if(staffRepository.existsById(staff.getUsername())) {
            if(staffRepository.findById(staff.getUsername()).get().getPassword().equals(staff.getPassword()))
                return new ModelAndView("redirect:/rest/staff/home");
        }
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView displayHomePage()
    {
        ModelAndView modelAndView = new ModelAndView("staffHome");
        return modelAndView;
    }

    @PostMapping("/home")
    public ModelAndView acceptUser(String username)
    {
        if(userRepository.existsById(username))
        {
            User u = userRepository.findById(username).get();
            u.setAccepted(true);
            userRepository.save(u);
        }
        ModelAndView modelAndView = new ModelAndView("staffHome");
        return modelAndView;
    }

    @GetMapping("home/authors")
    public ModelAndView filterAuthors()
    {
        ModelAndView modelAndView = new ModelAndView("viewAuthors");
        return modelAndView;
    }
    @PostMapping("home/authors")
    public ModelAndView filterAuthors1(String author1)
    {
        List<Book> books = bookRepository.findAll();
        List<Book> books1= new ArrayList<Book>();
        for(Book b : books)
            if(b.getAuthor().toUpperCase().contains(author1.toUpperCase()))
                books1.add(b);
        ModelAndView modelAndView = new ModelAndView("viewAuthors");
        modelAndView.addObject("authors",books1);
        return modelAndView;
    }

    @GetMapping("home/titles")
    public ModelAndView filterTitles()
    {
        ModelAndView modelAndView = new ModelAndView("viewTitles");
        return modelAndView;
    }
    @PostMapping("home/titles")
    public ModelAndView filterTitles1(String title1)
    {
        List<Book> books = bookRepository.findAll();
        List<Book> books1= new ArrayList<Book>();
        for(Book b : books)
            if(b.getTitle().toUpperCase().contains(title1.toUpperCase()))
                books1.add(b);
        ModelAndView modelAndView = new ModelAndView("viewTitles");
        modelAndView.addObject("titles",books1);
        return modelAndView;
    }

    @GetMapping("home/genres")
    public ModelAndView filterGenres()
    {
        ModelAndView modelAndView = new ModelAndView("viewGenres");
        return modelAndView;
    }
    @PostMapping("home/genres")
    public ModelAndView filterGenres1(String genre1)
    {
        List<Book> books = bookRepository.findAll();
        List<Book> books1= new ArrayList<Book>();
        for(Book b : books)
            if(b.getGenre().toUpperCase().contains(genre1.toUpperCase()))
                books1.add(b);
        ModelAndView modelAndView = new ModelAndView("viewGenres");
        modelAndView.addObject("genres",books1);
        return modelAndView;
    }

    @GetMapping
    public List<Staff> getAll() {
        return staffRepository.findAll();
    }

    @PostMapping
    public List<Staff> persist(@RequestBody final Staff staff) {
        staffRepository.save(staff);
        return staffRepository.findAll();
    }

    /*@GetMapping(value = "/{username}")
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
    */
}
