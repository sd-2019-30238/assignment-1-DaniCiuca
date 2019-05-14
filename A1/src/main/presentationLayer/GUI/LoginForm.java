package main.presentationLayer.GUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame{
    private JLabel usernameField;
    private JLabel passwordField;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel loginPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton staffLoginButton;
    private JLabel newAccountLabel;

    public LoginForm()
    {
        add(loginPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("LOGIN");
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

    public void RegisterListener(ActionListener listenForRegisterButton)
    {
        registerButton.addActionListener(listenForRegisterButton);
    }

    public void LoginStaffListener(ActionListener listenForLoginStaffButton)
    {
        staffLoginButton.addActionListener(listenForLoginStaffButton);
    }
}
