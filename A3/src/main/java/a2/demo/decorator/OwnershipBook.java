package a2.demo.decorator;

import a2.demo.model.Book;

public class OwnershipBook implements IOwnershipBook {

    private Book book;

    public OwnershipBook(Book book) {
        this.book = book;
    }

    @Override
    public int getAvailability() {
        return book.getAvailable();
    }

    @Override
    public void changeAvailability() {
        if(book.getAvailable()==1)
            book.setAvailable(0);
        else
            book.setAvailable(1);
    }
}
