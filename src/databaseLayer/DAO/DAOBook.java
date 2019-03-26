package databaseLayer.DAO;

import bussinessLayer.Model.Book;
import databaseLayer.Connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Logger;

public class DAOBook {
    protected static final Logger LOGGER = Logger.getLogger(DAOBook.class.getName());

    public String list(Book[] books) {
        String rez="";
        for(Book rs: books)
        {
            String title = rs.getTitle();
            String author = rs.getAuthor();
            String genre = rs.getGenre();
            Date releaseDate = rs.getReleaseDate();
            rez += "Title -> "+title+"\nAuthor -> "+author+"\nGenre -> "+genre+"\nRelease Date -> "
                + releaseDate+"\n\n";
        }
        return rez;
    }

    public String filterByTitle(String title)
    {
        String rez = "";
        Book[] books = new Book[100];
        int i=0;
        Connection dbConnection = ConnectionFactory.getConnection();
        try{
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM books WHERE title LIKE '%"+title+"%'");
            while ( rs.next() )
            {
                books[i] = new Book(rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getDate(5));
                i++;
            }
        }
        catch (Exception e){
            System.out.println("No results found");
        }
        for(int j = 0;j < i ; j++)
        {
            rez = rez + "Title -> "+books[j].getTitle()+"\nAuthor -> "+books[j].getAuthor()+"\nGenre -> "+
                    books[j].getGenre()+"\nRelease Date -> " + books[j].getReleaseDate()+"\n\n";
        }
        return rez;
    }

    public String filterByAuthor(String author)
    {
        String rez = "";
        Book[] books = new Book[100];
        int i=0;
        Connection dbConnection = ConnectionFactory.getConnection();
        try{
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM books WHERE author LIKE '%"+author+"%'");
            while ( rs.next() )
            {
                books[i] = new Book(rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getDate(5));
                i++;
            }
        }
        catch (Exception e){
            System.out.println("No results found");
        }
        for(int j = 0;j < i ; j++)
        {
            rez = rez + "Title -> "+books[j].getTitle()+"\nAuthor -> "+books[j].getAuthor()+"\nGenre -> "+
                    books[j].getGenre()+"\nRelease Date -> " + books[j].getReleaseDate()+"\n\n";
        }
        return rez;
    }

    public String filterByGenre(String genre)
    {
        String rez = "";
        Book[] books = new Book[100];
        int i=0;
        Connection dbConnection = ConnectionFactory.getConnection();
        try{
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM books WHERE genre LIKE '%"+genre+"%'");
            while ( rs.next() )
            {
                books[i] = new Book(rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getDate(5));
                i++;
            }
        }
        catch (Exception e){
            System.out.println("No results found");
        }
        for(int j = 0;j < i ; j++)
        {
            rez = rez + "Title -> "+books[j].getTitle()+"\nAuthor -> "+books[j].getAuthor()+"\nGenre -> "+
                    books[j].getGenre()+"\nRelease Date -> " + books[j].getReleaseDate()+"\n\n";
        }
        return rez;
    }

    public String filterByReleaseDate(String date)
    {
        String rez = "";
        Book[] books = new Book[100];
        int i=0;
        Connection dbConnection = ConnectionFactory.getConnection();
        try{
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM books WHERE release_date LIKE '%"+date+"%'");
            while ( rs.next() )
            {
                books[i] = new Book(rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getDate(5));
                i++;
            }
        }
        catch (Exception e){
            System.out.println("No results found");
        }
        for(int j = 0;j < i ; j++)
        {
            rez = rez + "Title -> "+books[j].getTitle()+"\nAuthor -> "+books[j].getAuthor()+"\nGenre -> "+
                    books[j].getGenre()+"\nRelease Date -> " + books[j].getReleaseDate()+"\n\n";
        }
        return rez;
    }
}
