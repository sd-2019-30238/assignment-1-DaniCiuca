package test;

import main.databaseLayer.DAO.DAOBook;
import main.databaseLayer.DAO.DAOStaff;
import main.databaseLayer.DAO.DAOUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJunit {
    @Test
    public void registerUser()
    {
        DAOUser daoUser = new DAOUser();
        DAOStaff daoStaff = new DAOStaff();
        daoUser.insert("test","test","month");
        daoStaff.acceptUser("test");
        assertTrue (daoUser.searchUser("test","test"));
    }

    @Test
    public void borrowBook()
    {
        DAOUser daoUser = new DAOUser();
        DAOBook daoBook = new DAOBook();
        int nr = daoBook.numberOfBooks();
        daoUser.borrowBook("Orase","test");
        assertEquals(nr+1,daoBook.numberOfBooks());
    }

    @Test
    public void returnBook()
    {
        DAOUser daoUser = new DAOUser();
        DAOBook daoBook = new DAOBook();
        daoUser.borrowBook("info","test");
        int nr = daoBook.numberOfBooks();
        daoUser.returnBook("info","test");
        assertEquals(nr-1,daoBook.numberOfBooks());
    }
}
