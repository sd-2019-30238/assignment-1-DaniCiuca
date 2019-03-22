import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOUser {
    protected static final Logger LOGGER = Logger.getLogger(DAOUser.class.getName());

    public void deleteBy(String username) {
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            PreparedStatement st = dbConnection.prepareStatement("DELETE FROM users WHERE username =  ?");
                try{
                        st.setObject(1, username );
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            st.executeUpdate();
            ConnectionFactory.close(st);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:deleteByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
    }

    public void insert(String username, String password) {
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            PreparedStatement st = dbConnection.prepareStatement("INSERT INTO users VALUES (?,?)");
            try{
                st.setObject(1, username );
                st.setObject(2, password );
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

    public void edit(String username, String password) {
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            PreparedStatement st = dbConnection.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
            try{
                st.setObject(1, password );
                st.setObject(2, username );
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            st.executeUpdate();
            ConnectionFactory.close(st);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:edit " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
    }

    public User[] list() {
        User[] users=new User[100];
        int i=0;
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM users");
            while ( rs.next() )
            {
                String username = rs.getString("username");
                String password=rs.getString("password");
                users[i++]=new User(username,password);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:list " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
        return users;
    }
}
