import javax.swing.*;
import java.awt.event.ActionListener;

public class UserMenuForm extends JFrame{
    private JPanel userMenuPanel;
    private JTextField textField1;
    private JLabel titleLabel;
    private JButton returnButton;
    private JButton borrowButton;
    private JTextArea textArea1;
    private JLabel usernameLabel;

    public UserMenuForm()
    {
        add(userMenuPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("MENU");
        setSize(400,400);
    }

    public String getTextField1() {
        return textField1.getText();
    }

    public void setTextField1(String textField1) {
        this.textField1.setText(textField1);
    }

    public String getTextArea1() {
        return textArea1.getText();
    }

    public void setTextArea1(String textArea1) {
        this.textArea1.setText(textArea1);
    }

    public String getUsername()
    {
        return usernameLabel.getText();
    }

    public void setUsername(String user)
    {
        this.usernameLabel.setText(this.usernameLabel.getText()+user);
    }

    public void BorrowListener(ActionListener listenForBorrowButton)
    {
        borrowButton.addActionListener(listenForBorrowButton);
    }

    public void ReturnListener(ActionListener listenForReturnButton)
    {
        returnButton.addActionListener(listenForReturnButton);
    }
}

