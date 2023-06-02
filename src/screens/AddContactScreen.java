package screens;

import contacts.Contact;
import controls.contactController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddContactScreen extends JFrame {
    private JTextField nameField;
    private JTextField homePhoneField;
    private JTextField workPhoneField;
    private JTextField addressField;
    private JButton saveButton;
    private JPanel contactPanel;
    private JLabel nameLabel;
    private JLabel homePhoneLabel;
    private JLabel workPhoneLabel;
    private JLabel addressLabel;
    private JLabel infoLabel;

    public AddContactScreen() {
        nameLabel.setText("<html>Name: <small><font color='red'>  *Required</font></small></html>");
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        homePhoneLabel.setText("<html>Home Phone: <small><font color='red'>  *Required</font></small></html>");
        setContentPane(contactPanel);
        setSize(500, 375);
        this.setResizable(false);
        setLocationRelativeTo(null);
//        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);





        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String homePhone = homePhoneField.getText();
                String workPhone = workPhoneField.getText();
                String address = addressField.getText();

                String text = contactController.verifyUserInput(name,homePhone,workPhone,address);

                infoLabel.setFont(new Font("Serif", Font.PLAIN, 25));
                if(text.equals("Contact \"" + name + "\" added successfully")) {
                    infoLabel.setForeground(Color.blue);
                    infoLabel.setText(text);
                } else {
                    infoLabel.setForeground(Color.RED);
                    infoLabel.setText(text);
                }

                infoLabel.setText(text);


                System.out.println(contactController.getContacts());


            }
        });
    }

}
