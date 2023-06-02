package screens;

import contacts.Contact;
import controls.contactController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {
    private JPanel mainPanel;
    private JButton viewButton;
    private JButton addButton;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton exitButton;
    private JPanel buttonPanel;
    private JLabel welcomeLabel;
    private JLabel promptLabel;

    public MainScreen() {
        welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 25));
        promptLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        setContentPane(mainPanel);
        setTitle("Welcome");
        setSize(500, 375);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new View();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contactController.exit();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddContactScreen();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchScreen();

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteScreen2();

            }
        });
    }

    public static void main(String[] args) {
        contactController.loadContacts();
        new MainScreen();
    }
}
