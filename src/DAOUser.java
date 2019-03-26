import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOUser {
    protected static final Logger LOGGER = Logger.getLogger(DAOUser.class.getName());

    public void insert(String username, String password, String paymentMethod) {
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            PreparedStatement st = dbConnection.prepareStatement("INSERT INTO users_waiting VALUES (?,?,?)");
            try{
                User user = new User(username,password,paymentMethod);
                st.setObject(1, user.getUsername() );
                st.setObject(2, user.getPassword() );
                st.setObject(3, user.getPaymentMethod().getType());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            st.executeUpdate();
            ConnectionFactory.close(st);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
    }

    public boolean searchUser(String user, String pass)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM users");
            while ( rs.next() )
            {
                String username = rs.getString("username");
                String password=rs.getString("password");
                if(user.equalsIgnoreCase(username)&&pass.equalsIgnoreCase(password))
                    return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:list " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
        return false;
    }

    public User returnUser(String user, String pass)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM users");
            while ( rs.next() )
            {
                String username = rs.getString("username");
                String password=rs.getString("password");
                String paymentMethod = rs.getString("paymentMethod");
                if(user.equalsIgnoreCase(username)&&pass.equalsIgnoreCase(password))
                    return (new User(username,password,paymentMethod));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:list " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    public boolean borrowBook(String title,String username)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM books WHERE title = '"+title+"'");
            rs.next();
            int id = rs.getInt("id");
            rs.getString("title");
            String author = rs.getString("author");
            String genre = rs.getString("genre");
            Date date = rs.getDate("release_date");
            int available = rs.getInt("available");
            if(available == 1)
            {
                System.out.println("Book Available");
                PreparedStatement st = dbConnection.prepareStatement("UPDATE books SET available = 0 WHERE id = ?");
                PreparedStatement st2 = dbConnection.prepareStatement("INSERT INTO borrow_list(bookID,username) VALUES (?,?)");
                try
                {
                    st.setObject(1,id);
                    st2.setObject(1,id);
                    st2.setObject(2,username);
                }
                catch (IllegalArgumentException e)
                {
                    e.printStackTrace();
                }
                st.executeUpdate();
                st2.executeUpdate();
                ConnectionFactory.close(st);
                ConnectionFactory.close(st2);
            }
            else
            {
                System.out.println("Book Unavailable");
                PreparedStatement st = dbConnection.prepareStatement("INSERT INTO waiting_list (BookID,username) VALUES (?,?)");
                try{
                    st.setObject(1, id );
                    st.setObject(2, username );
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                st.executeUpdate();
                ConnectionFactory.close(st);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"USerDAO:borrow " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
        return false;
    }

    public void returnBook(String title, String username)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT id FROM books WHERE title = '"+title+"'");
            rs.next();
            int id = rs.getInt("id");
            PreparedStatement st = dbConnection.prepareStatement("DELETE FROM borrow_list WHERE BookID = ? AND username = ?");
            try {
                st.setObject(1, id);
                st.setObject(2,username);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            stmt = dbConnection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM waiting_list WHERE BookID = "+id+" LIMIT 1");
            if(rs.next())
            {
                String u = rs.getString("username");
                PreparedStatement st3 = dbConnection.prepareStatement("DELETE FROM waiting_list WHERE BookID = ? AND username = ?");
                try {
                    st3.setObject(1, id);
                    st3.setObject(2,u);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                st3.executeUpdate();
                ConnectionFactory.close(st3);

                PreparedStatement st4 = dbConnection.prepareStatement("INSERT INTO borrow_list(BookID,username) VALUES(?,?)");
                try {
                    st4.setObject(1, id);
                    st4.setObject(2, u);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                st4.executeUpdate();
                ConnectionFactory.close(st4);
            }
            else
            {
                PreparedStatement st2 = dbConnection.prepareStatement("UPDATE books SET available = 1 WHERE id = ?");
                try {
                    st2.setObject(1, id);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
                st2.executeUpdate();
                ConnectionFactory.close(st2);
            }
            st.executeUpdate();
            ConnectionFactory.close(st);
        }
        catch (SQLException e) {
            LOGGER.log(Level.WARNING,"UserDAO:returnBook " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
    }
}
