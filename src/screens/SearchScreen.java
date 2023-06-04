package screens;

import contacts.Contact;
import controls.contactController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SearchScreen extends JFrame {


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

    public SearchScreen(JPanel resultsContainer){
        setSize(500, 375);
        this.setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel main = new JPanel(new BorderLayout());
        JPanel searchPromptContainer = new JPanel(new BorderLayout());
        JLabel searchPrompt = new JLabel("Search for a Contact:");
        searchPrompt.setBorder(new EmptyBorder(0, 10, 10, 10));
        searchPrompt.setHorizontalAlignment(JLabel.CENTER);
        searchPrompt.setFont(new Font("Serif", Font.PLAIN, 20));
        searchPromptContainer.add(searchPrompt, BorderLayout.NORTH);
        JTextField name = new JTextField();
        JPanel textContainer = new JPanel();
        name.setPreferredSize(new Dimension(250,30));
        textContainer.add(name);
        JPanel buttonContainer = new JPanel(new FlowLayout());
        JButton searchButton = new JButton("Search");
        searchButton.setMaximumSize(new Dimension(100,30));
        buttonContainer.add(searchButton,BorderLayout.CENTER);
        searchPromptContainer.add(textContainer, BorderLayout.CENTER);
        searchPromptContainer.add(buttonContainer,BorderLayout.SOUTH);
        main.add(searchPromptContainer, BorderLayout.NORTH);
        main.add(resultsContainer, BorderLayout.SOUTH);
        main.setBorder(new EmptyBorder(40, 40, 40, 40));
        add(main, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);


        searchButton.addActionListener(e -> {
            String userInput = name.getText();

//                performSearch(userInput);

            int listSize = contactController.getContacts().size();
            boolean found = false;
            for(int i = 0; i < listSize; i++){
                if(contactController.getContacts().get(i).getName().equalsIgnoreCase(userInput)) {
                    found = true;
                    new SearchScreen(locatedResults(contactController.getContacts().get(i)));
                    dispose();
                }
            }
            if(!found){

                new SearchScreen(resultsPanelTemplate("No Matching Contacts Found"));
                dispose();
            }
        });
    }


}
