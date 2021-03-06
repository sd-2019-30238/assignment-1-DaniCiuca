package a2.demo.command;

import a2.demo.model.Book;
import a2.demo.model.Borrow;
import a2.demo.model.Waiting;
import a2.demo.repository.BookWriteRepository;
import a2.demo.repository.BorrowWriteRepository;
import a2.demo.repository.WaitingWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BorrowBookCommandHandler implements ICommandHandler {
    @Autowired
    BookWriteRepository bookRepository;

    @Autowired
    BorrowWriteRepository borrowRepository;

    @Autowired
    WaitingWriteRepository waitingRepository;

    String username;

    @Override
    public void execute(ICommand command) {
        Book book = (Book) command;
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
    }
}
