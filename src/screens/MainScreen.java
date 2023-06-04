package screens;

import controls.contactController;
import javax.swing.*;
import java.awt.*;
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

        viewButton.addActionListener(e -> new View(View.createListingPanelForAll()));
        exitButton.addActionListener(e -> {
            try {
                contactController.exit();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Something went wrong. Your contacts may be lost.",
                        "Uh Oh", JOptionPane.ERROR_MESSAGE);
            }
        });
        addButton.addActionListener(e -> new AddContactScreen());
        searchButton.addActionListener(e -> new SearchOrDelete(new JPanel(), new JButton("Search")));
        deleteButton.addActionListener(e -> new SearchOrDelete(new JPanel(), new JButton("Search and Delete")));
    }

    public static void main(String[] args) {
        contactController.loadContacts();
        new MainScreen();
    }
}
