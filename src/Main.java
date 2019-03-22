
public class Main {
    public static void main(String[] args) throws Exception {
        ConnectionFactory dao = new ConnectionFactory();
        dao.createConnection();
        DAOUser theModel=new DAOUser();
        DAOBook bookDAO=new DAOBook();
        bookDAO.insert("Orase de hartie","John Green","jnfssf","2009-04-23");
        Book[] books=bookDAO.list();
        System.out.println("Title = "+books[0].getTitle()+"\nAuthor = "+books[0].getAuthor()
                +"\nGenre = "+books[0].getGenre()+"\nRelease Date = "+books[0].getReleaseDate());
    }
}
