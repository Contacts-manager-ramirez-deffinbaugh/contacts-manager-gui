package screens;
import controls.contactController;

import javax.swing.*;
import java.awt.*;

public class AddContactScreen extends JFrame {
    private JTextField nameField;
    private JTextField homePhoneField;
    private JTextField workPhoneField;
    private JTextField addressField;
    private JButton saveButton;
    private JPanel contactPanel;
    private JLabel nameLabel;

    private JLabel addressLabel;
    private JLabel workPhoneLabel;
    private JLabel homePhoneLabel;
    private JLabel infoLabel;

    public AddContactScreen() {
        nameLabel.setText("<html>Name: <small><font color='red'>  *Required</font></small></html>");
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        homePhoneLabel.setText("<html>Home Phone: <small><font color='red'>  *Required</font></small></html>");
        setContentPane(contactPanel);
        setSize(500, 375);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);


        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String homePhone = homePhoneField.getText();
            String workPhone = workPhoneField.getText();
            String address = addressField.getText();

            String text = contactController.verifyUserInput2(name,homePhone,workPhone,address);

            infoLabel.setFont(new Font("Serif", Font.PLAIN, 25));
            if(text.equals("Contact \"" + name + "\" added successfully")) {
                infoLabel.setForeground(Color.blue);
                infoLabel.setText(text);
            } else {
                infoLabel.setForeground(Color.RED);
                infoLabel.setText(text);
            }
        });
    }

}
