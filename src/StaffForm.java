import javax.swing.*;
import java.awt.event.ActionListener;

public class StaffForm extends JFrame{
    private JPanel staffPanel;
    private JTextField textField1;
    private JButton filterByReleaseDateButton;
    private JButton filterByGenreButton;
    private JButton filterByAuthorButton1;
    private JButton filterByTitleButton;
    private JButton acceptUserButton;
    private JTextField textField2;
    private JLabel usernameLabel;
    private JTextPane textPane1;

    public StaffForm()
    {
        add(staffPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("STAFF MENU");
        setSize(400,400);
    }

    public String getTextField1() {
        return textField1.getText();
    }

    public void setTextField1(String textField1) {
        this.textField1.setText(textField1);
    }

    public void setTextArea1(String textArea1) {
        this.textPane1.setText(textArea1);
    }

    public String getTextField2() {
        return textField2.getText();
    }

    public void setTextField2(String textField2) {
        this.textField2.setText(textField2);
    }

    public void AcceptListener(ActionListener listenForAcceptButton)
    {
        acceptUserButton.addActionListener(listenForAcceptButton);
    }

    public void FilterAuthorListener(ActionListener listenForFilterAuthorButton)
    {
        filterByAuthorButton1.addActionListener(listenForFilterAuthorButton);
    }

    public void FilterTitleListener(ActionListener listenForFilterTitleButton)
    {
        filterByTitleButton.addActionListener(listenForFilterTitleButton);
    }

    public void FilterGenreListener(ActionListener listenForFilterGenreButton)
    {
        filterByGenreButton.addActionListener(listenForFilterGenreButton);
    }

    public void FilterDateListener(ActionListener listenForFilterDateButton)
    {
        filterByReleaseDateButton.addActionListener(listenForFilterDateButton);
    }
}
