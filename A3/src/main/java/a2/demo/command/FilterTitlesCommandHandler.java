package a2.demo.command;

import a2.demo.model.Book;
import a2.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FilterTitlesCommandHandler implements ICommandHandler {
    @Autowired
    BookRepository bookRepository;


    @Override
    public void execute(ICommand command) {
        Book book = (Book) command;
        List<Book> books = bookRepository.findAll();
        List<Book> books1= new ArrayList<Book>();
        for(Book b : books)
            if(b.getTitle().toUpperCase().contains(book.getTitle().toUpperCase()))
                books1.add(b);
    }
}
