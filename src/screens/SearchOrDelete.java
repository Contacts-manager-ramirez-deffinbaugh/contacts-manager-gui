package screens;

import contacts.Contact;
import controls.contactController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class SearchOrDelete extends JFrame {



    public static JPanel locatedResults(Contact contact) {
        JPanel resultsContainer = resultsPanelTemplate("1 Matching Contact(s)");
        JPanel contactInfo = View.createListingPanelForOne(contact);
        resultsContainer.add(contactInfo, BorderLayout.SOUTH);
        return resultsContainer;
    }


    public static JPanel resultsPanelTemplate(String message) {
        JPanel resultsContainer = new JPanel(new BorderLayout());
        JLabel resultText = new JLabel(message);
        resultText.setHorizontalAlignment(JLabel.CENTER);
        resultText.setFont(new Font("Serif", Font.PLAIN, 25));
        resultsContainer.add(resultText, BorderLayout.NORTH);
        return resultsContainer;
    }

    public SearchOrDelete(JPanel resultsContainer, JLabel prompt, JButton button){
        setSize(500, 375);
        this.setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel main = new JPanel(new BorderLayout());
        JPanel searchPromptContainer = new JPanel(new BorderLayout());
        prompt.setBorder(new EmptyBorder(0, 10, 10, 10));
        prompt.setHorizontalAlignment(JLabel.CENTER);
        prompt.setFont(new Font("Serif", Font.PLAIN, 20));
        searchPromptContainer.add(prompt, BorderLayout.NORTH);
        JTextField name = new JTextField();
        JPanel textContainer = new JPanel();
        name.setPreferredSize(new Dimension(250,30));
        textContainer.add(name);
        JPanel buttonContainer = new JPanel(new FlowLayout());
        button.setMaximumSize(new Dimension(100,30));
        buttonContainer.add(button,BorderLayout.CENTER);
        searchPromptContainer.add(textContainer, BorderLayout.CENTER);
        searchPromptContainer.add(buttonContainer,BorderLayout.SOUTH);
        main.add(searchPromptContainer, BorderLayout.NORTH);
        main.add(resultsContainer, BorderLayout.SOUTH);
        main.setBorder(new EmptyBorder(40, 40, 40, 40));
        add(main, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);


        button.addActionListener(e -> {
            if(button.getText().equals("Search and Delete")) {
                DeleteContact(name);
            }
            else {
                SearchForContact(name);
            }
        });
    }

    public void SearchForContact(JTextField name) {
        String userInput = name.getText();
        int listSize = contactController.getContacts().size();
        boolean found = false;
        for(int i = 0; i < listSize; i++){
            if(contactController.getContacts().get(i).getName().equalsIgnoreCase(userInput)) {
                found = true;
                new SearchOrDelete(locatedResults(contactController.getContacts().get(i)),new JLabel("Search for a Contact"), new JButton("Search"));
                dispose();
            }
        }
        if(!found){

            new SearchOrDelete(resultsPanelTemplate("No Matching Contacts Found"),new JLabel("Search for a Contact"), new JButton("Search"));
            dispose();
        }
    }

    public void DeleteContact(JTextField name) {
        String userInput = name.getText();
        int listSize = contactController.getContacts().size();
        boolean found = false;
        for(int i = 0; i < listSize; i++){
            if(contactController.getContacts().get(i).getName().equalsIgnoreCase(userInput)) {
                SearchOrDelete showContact = new SearchOrDelete(locatedResults(contactController.getContacts().get(i)),new JLabel("Search for a Contact to Delete"), new JButton("Search and Delete"));
                found = true;
                int input = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this contact? It cannot be undone!", "Select an Option...", JOptionPane.YES_NO_OPTION);
                // 0=yes, 1=no
                if(input == 0) {
                    contactController.getContacts().remove(contactController.getContacts().get(i));
                    new SearchOrDelete(resultsPanelTemplate("Contact was successfully deleted"),new JLabel("Search for a Contact to Delete"), new JButton("Search and Delete"));
                    showContact.dispose();
                }

            } dispose();
        }
        if(!found){
            new SearchOrDelete(resultsPanelTemplate("No Matching Contacts Found"),new JLabel("Search for a Contact to Delete"), new JButton("Search and Delete"));
            dispose();
        }
    }



}
