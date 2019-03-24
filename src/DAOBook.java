import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOBook {
    protected static final Logger LOGGER = Logger.getLogger(DAOBook.class.getName());

    public void deleteById(int id){
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            PreparedStatement st = dbConnection.prepareStatement("DELETE FROM books WHERE id =  ?");
            try{
                st.setObject(1, id );
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            st.executeUpdate();
            ConnectionFactory.close(st);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"BookDAO:deleteByID " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
    }

    public void insert(String title, String author, String genre, String releaseDate) {
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            PreparedStatement st = dbConnection.prepareStatement("INSERT INTO books " +
                    "(title,author,genre,release_date) VALUES (?,?,?,?)");
            try{
                st.setObject(1, title );
                st.setObject(2, author );
                st.setObject(3, genre );
                st.setObject(4, releaseDate );
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            st.executeUpdate();
            ConnectionFactory.close(st);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"BookDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
    }

    public void edit(int id, String title, String author, String genre, Date releaseDate) {
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            PreparedStatement st = dbConnection.prepareStatement("UPDATE books SET title = ?, author = ?," +
                    " genre = ?, release_date = ?  WHERE id = ?");
            try{
                st.setObject(1, title );
                st.setObject(2, author );
                st.setObject(3, genre );
                st.setObject(4, releaseDate );
                st.setObject(5, id );
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            st.executeUpdate();
            ConnectionFactory.close(st);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"BookDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
    }

    public Book[] list() {
        Book[] books=new Book[100];
        int i=0;
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM books");
            while ( rs.next() )
            {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author=rs.getString("author");
                String genre=rs.getString("genre");
                Date releaseDate=rs.getDate("release_date");
                books[i]=new Book(title,genre,genre,releaseDate);
                books[i].setId(id);
                i++;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"BookDAO:list " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
        return books;
    }

    void filterByTitle(String title)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        try{
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM books WHERE title LIKE '%"+title+"%'");
            while ( rs.next() )
            {
                System.out.println(rs.getString(2)+" "+rs.getString(3)+" "
                +rs.getString(4)+" "+rs.getDate(5));
            }
        }
        catch (Exception e){
            System.out.println("No results found");
        }
    }

    void filterByAuthor(String author)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        try{
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM books WHERE author LIKE '%"+author+"%'");
            while ( rs.next() )
            {
                System.out.println(rs.getString(2)+" "+rs.getString(3)+" "
                        +rs.getString(4)+" "+rs.getDate(5));
            }
        }
        catch (Exception e){
            System.out.println("No results found");
        }
    }

    void filterByGenre(String genre)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        try{
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM books WHERE genre LIKE '%"+genre+"%'");
            while ( rs.next() )
            {
                System.out.println(rs.getString(2)+" "+rs.getString(3)+" "
                        +rs.getString(4)+" "+rs.getDate(5));
            }
        }
        catch (Exception e){
            System.out.println("No results found");
        }
    }

    void filterByReleaseDate(String date)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        try{
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM books WHERE release_date = '"+date+"'");
            while ( rs.next() )
            {
                System.out.println(rs.getString(2)+" "+rs.getString(3)+" "
                        +rs.getString(4)+" "+rs.getDate(5));
            }
        }
        catch (Exception e){
            System.out.println("No results found");
        }
    }
}
