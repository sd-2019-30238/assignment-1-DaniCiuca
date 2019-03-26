import javax.swing.*;
import java.awt.event.ActionListener;

public class StaffLoginForm extends JFrame{
    private JPanel staffLoginPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton backToLoginButton;

    public StaffLoginForm()
    {
        add(staffLoginPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("LOGIN STAFF");
        setSize(400,400);
    }

    public String getTextField1() {
        return textField1.getText();
    }

    public void setTextField1(String textField1) {
        this.textField1.setText(textField1);
    }

    public String getPasswordField1() {
        return passwordField1.getText();
    }

    public void setPasswordField1(String passwordField1) {
        this.passwordField1.setText(passwordField1);
    }

    public void LoginListener(ActionListener listenForLoginButton)
    {
        loginButton.addActionListener(listenForLoginButton);
    }

    public void BackListener(ActionListener listenForBackButton)
    {
        backToLoginButton.addActionListener(listenForBackButton);
    }
}
