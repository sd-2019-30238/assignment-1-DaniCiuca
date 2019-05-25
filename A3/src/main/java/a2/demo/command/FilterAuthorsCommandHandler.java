package a2.demo.command;

import a2.demo.model.Book;
import a2.demo.repository.BookReadRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FilterAuthorsCommandHandler implements ICommandHandler {
    @Autowired
    BookReadRepository bookRepository;


    @Override
    public void execute(ICommand command) {
        Book book = (Book) command;
        List<Book> books = bookRepository.findAll();
        List<Book> books1= new ArrayList<Book>();
        for(Book b : books)
            if(b.getAuthor().toUpperCase().contains(book.getAuthor().toUpperCase()))
                books1.add(b);
    }
}
