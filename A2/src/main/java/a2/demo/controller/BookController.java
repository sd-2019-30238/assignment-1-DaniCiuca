package a2.demo.controller;

import a2.demo.model.Book;
import a2.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @PostMapping
    public List<Book> persist(@RequestBody final Book book) {
        bookRepository.save(book);
        return bookRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Book findBook(@PathVariable Integer id)
    {
        return bookRepository.findById(id).get();
    }

    @PutMapping(value = "/{id}")
    public Book updateBook(@PathVariable Integer id) {
        Book book = bookRepository.findById(id).get();
        book.setAvailable((book.getAvailable()+1)%2);
        return bookRepository.save(book);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Integer id) {
        bookRepository.deleteById(id);
    }
}
