package bussinessLayer.Controller;

import bussinessLayer.Model.User;
import databaseLayer.Connection.ConnectionFactory;
import databaseLayer.DAO.DAOBook;
import databaseLayer.DAO.DAOStaff;
import databaseLayer.DAO.DAOUser;
import presentationLayer.GUI.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Controller {
    private DAOBook daoBook;
    private DAOUser daoUser;
    private DAOStaff daoStaff;
    private LoginForm loginForm;
    private StaffForm staffForm;
    private UserMenuForm userMenuForm;
    private StaffLoginForm staffLoginForm;
    private RegisterForm registerForm;
    private boolean exit = false;
    private boolean exitStaff = false;
    private boolean exitUser = false;
    private User user;

    private Scanner scanner = new Scanner(System.in);

    public Controller() {
        ConnectionFactory dao = new ConnectionFactory();
        dao.createConnection();
        daoBook = new DAOBook();
        daoUser = new DAOUser();
        daoStaff = new DAOStaff();
        loginForm = new LoginForm();
        staffForm = new StaffForm();
        userMenuForm = new UserMenuForm();
        staffLoginForm = new StaffLoginForm();
        registerForm = new RegisterForm();
        loginForm.LoginListener(new LoginUserListener());
        loginForm.RegisterListener(new ToRegisterUserListener());
        loginForm.LoginStaffListener(new ToStaffLogin());
        staffForm.AcceptListener(new AcceptUserListener());
        staffForm.FilterAuthorListener(new FilterAuthor());
        staffForm.FilterDateListener(new FilterDate());
        staffForm.FilterGenreListener(new FilterGenre());
        staffForm.FilterTitleListener(new FilterTitle());
        userMenuForm.BorrowListener(new Borrow());
        userMenuForm.ReturnListener(new Return1());
        staffLoginForm.LoginListener(new LoginStaff());
        staffLoginForm.BackListener(new Back());
        registerForm.RegisterListener(new Register());
        registerForm.BackListener(new Back1());
        loginForm.setVisible(true);
    }

    class LoginUserListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String username = loginForm.getTextField1();
            String password = loginForm.getPasswordField1();
            if (daoUser.searchUser(username, password))
            {
                System.out.println("bussinessLayer.Model.User connected!!");
                loginForm.setVisible(false);
                user = daoUser.returnUser(username,password);
                userMenuForm.setVisible(true);
                userMenuForm.setUsername(username);
            }
            else
                System.out.println("bussinessLayer.Model.User not find!!");
        }
    }

    class ToRegisterUserListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            loginForm.setVisible(false);
            registerForm.setVisible(true);
        }
    }

    class AcceptUserListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            daoStaff.acceptUser(staffForm.getTextField1());
            System.out.println("bussinessLayer.Model.User: "+staffForm.getTextField1()+" was accepted.");
        }
    }

    class FilterAuthor implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            staffForm.setTextArea1(daoBook.filterByAuthor(staffForm.getTextField2()));
        }
    }

    class FilterTitle implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            staffForm.setTextArea1(daoBook.filterByTitle(staffForm.getTextField2()));
        }
    }

    class FilterGenre implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            staffForm.setTextArea1(daoBook.filterByGenre(staffForm.getTextField2()));
        }
    }

    class FilterDate implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            staffForm.setTextArea1(daoBook.filterByReleaseDate(staffForm.getTextField2()));
        }
    }

    class Borrow implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            daoUser.borrowBook(userMenuForm.getTextField1(),user.getUsername());
        }
    }

    class Return1 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            daoUser.returnBook(userMenuForm.getTextField1(),user.getUsername());
        }
    }

    class LoginStaff implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String username = staffLoginForm.getTextField1();
            String password = staffLoginForm.getPasswordField1();
            if (daoStaff.searchUser(username, password)) {
                System.out.println("bussinessLayer.Model.Staff connected!!");
                staffLoginForm.setVisible(false);
                staffForm.setVisible(true);
            } else
                System.out.println("bussinessLayer.Model.Staff not find!!");
        }
    }

    class Register implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String username = registerForm.getTextField1();
            String password = registerForm.getPasswordField1();
            String paymentMethod = registerForm.getComboBox1();
            daoUser.insert(username, password, paymentMethod);
            System.out.println("\nThe user was register!!!");
        }
    }

    class Back implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            staffLoginForm.setVisible(false);
            loginForm.setVisible(true);
        }
    }

    class ToStaffLogin implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            loginForm.setVisible(false);
            staffLoginForm.setVisible(true);
        }
    }

    class Back1 implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            registerForm.setVisible(false);
            loginForm.setVisible(true);
        }
    }
}
