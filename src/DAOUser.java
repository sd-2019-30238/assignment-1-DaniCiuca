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
                String paymentMethod = rs.getString("paymentMethod");
                users[i++]=new User(username,password,paymentMethod);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:list " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
        return users;
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
}
