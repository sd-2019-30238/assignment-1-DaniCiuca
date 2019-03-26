package databaseLayer.DAO;

import databaseLayer.Connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOStaff {
    protected static final Logger LOGGER = Logger.getLogger(DAOUser.class.getName());

    public void acceptUser(String username)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            PreparedStatement st = dbConnection.prepareStatement("DELETE FROM users_waiting WHERE username = ?");
            PreparedStatement st2 = dbConnection.prepareStatement("INSERT INTO users VALUES (?,?,?)");
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM users_waiting WHERE username = '"+username+"'");
            rs.next();
            String user = rs.getString("username");
            String password = rs.getString("password");
            String paymentMethod = rs.getString("paymentMethod");
            try{
                st.setObject(1, username);
                st2.setObject(1,user);
                st2.setObject(2,password);
                st2.setObject(3,paymentMethod);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            st.executeUpdate();
            st2.executeUpdate();
            ConnectionFactory.close(st);
            ConnectionFactory.close(st2);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"StaffDAO:accept " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
    }

    public boolean searchUser(String user, String pass) {
        Connection dbConnection = ConnectionFactory.getConnection();
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM staff");
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                if (user.equalsIgnoreCase(username) && pass.equalsIgnoreCase(password))
                    return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StaffDAO:login " + e.getMessage());
        } finally {
            ConnectionFactory.close(dbConnection);
        }
        return false;
    }
}
