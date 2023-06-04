package screens;

import contacts.Contact;
import controls.contactController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
               new View(View.createListingPanelForAll());
            }
        });
        exitButton.addActionListener(e -> {
            try {
                contactController.exit();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Something went wrong. Your contacts may be lost.",
                        "Uh Oh", JOptionPane.ERROR_MESSAGE);
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
//                new SearchScreen(new JPanel());
                new SearchOrDelete(new JPanel(),new JLabel("Search for a Contact to Delete"), new JButton("Search"));

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new DeleteScreen2(new JPanel());
                new SearchOrDelete(new JPanel(),new JLabel("Search for a Contact to Delete"), new JButton("Search and Delete"));

            }
        });
    }

    public static void main(String[] args) {
        contactController.loadContacts();
        new MainScreen();
    }
}
