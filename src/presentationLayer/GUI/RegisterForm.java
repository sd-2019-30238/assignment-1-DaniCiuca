package presentationLayer.GUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RegisterForm extends JFrame{
    private JPanel registerPanel;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton registerButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JComboBox comboBox1;
    private JLabel paymentPlanLabel;
    private JButton backToLoginButton;

    public RegisterForm()
    {
        add(registerPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Register");
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

    public String getComboBox1() {
        return comboBox1.getSelectedItem().toString();
    }

    public void RegisterListener(ActionListener listenForRegisterButton)
    {
        registerButton.addActionListener(listenForRegisterButton);
    }

    public void BackListener(ActionListener listenForBackButton)
    {
        backToLoginButton.addActionListener(listenForBackButton);
    }
}
