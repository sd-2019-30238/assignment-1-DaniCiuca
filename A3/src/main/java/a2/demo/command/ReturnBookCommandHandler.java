package a2.demo.command;

import a2.demo.model.Book;
import a2.demo.model.Borrow;
import a2.demo.model.Waiting;
import a2.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReturnBookCommandHandler implements ICommandHandler {
    @Autowired
    BookWriteRepository bookRepository;

    @Autowired
    BorrowWriteRepository borrowRepository;

    @Autowired
    WaitingWriteRepository waitingRepository;

    @Autowired
    BookReadRepository bookRepository1;

    @Autowired
    BorrowReadRepository borrowRepository1;

    @Autowired
    WaitingReadRepository waitingRepository1;

    String username;

    @Override
    public void execute(ICommand command) {
        Book book = (Book) command;
        List<Waiting> waiting = waitingRepository1.findAll();
        List<Borrow> borrows = borrowRepository1.findAll();
        for (Borrow a : borrows) {
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
    }
}
