package screens;

import contacts.Contact;
import controls.contactController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DeleteScreen2 extends JFrame {


    public DeleteScreen2(){
        setSize(500, 375);
        this.setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel main = new JPanel(new BorderLayout());

        JPanel searchPromptContainer = new JPanel(new BorderLayout());
//        searchPromptContainer.setBorder(BorderFactory.createLineBorder(Color.black));
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
        JButton searchButton2 = new JButton("Search");
        searchButton2.setMaximumSize(new Dimension(100,30));
        buttonContainer.add(searchButton2,BorderLayout.CENTER);
        searchPromptContainer.add(textContainer, BorderLayout.CENTER);
        searchPromptContainer.add(buttonContainer,BorderLayout.SOUTH);
        main.add(searchPromptContainer, BorderLayout.NORTH);


        main.setBorder(new EmptyBorder(40, 40, 40, 40));
        add(main, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);



        searchButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = name.getText();
                System.out.println("im running not");

//                performSearch(userInput);

                int listSize = contactController.getContacts().size();
                System.out.println(listSize);
                boolean found = false;
                for(int i = 0; i < listSize; i++){
                    if(contactController.getContacts().get(i).getName().equals(userInput)) {
                        String message = "<html>Are you sure you want to delete the contact \""+userInput+"\"?<br>It cannot be undone!</html>";
                        found = true;
                        int input = JOptionPane.showConfirmDialog(null,
                                message, "Select an Option...", JOptionPane.YES_NO_OPTION);

                        // 0=yes, 1=no, 2=cancel
                        System.out.println(input);
                        if(input == 0) {
                            contactController.getContacts().remove(contactController.getContacts().get(i));
                        }
                        dispose();
                    }
                }
                if(!found){

                    new SearchScreen(noLocate());
                    dispose();
                }


//

            }
        });
    }

    public static JPanel locatedResults(Contact contact) {
        JPanel resultsContainer = new JPanel(new BorderLayout());
//        resultsContainer.setBorder(BorderFactory.createLineBorder(Color.red));
        JLabel resultText = new JLabel("1 Matching Contact(s):");
        resultText.setFont(new Font("Serif", Font.PLAIN, 25));
        resultsContainer.add(resultText, BorderLayout.NORTH);
        JPanel contactInfo = View.createListingPanelForOne(contact);
//        contactInfo.setBorder(BorderFactory.createLineBorder(Color.blue));
        resultsContainer.add(contactInfo, BorderLayout.SOUTH);
        return resultsContainer;
    }

    public static JPanel noLocate() {
        JPanel resultsContainer = new JPanel(new BorderLayout());
//        resultsContainer.setBorder(BorderFactory.createLineBorder(Color.red));
        JLabel resultText = new JLabel("No Matching Contacts");
        resultText.setFont(new Font("Serif", Font.PLAIN, 25));
        resultsContainer.add(resultText, BorderLayout.NORTH);
//        contactInfo.setBorder(BorderFactory.createLineBorder(Color.blue));
//        resultsContainer.add(contactInfo, BorderLayout.SOUTH);
        return resultsContainer;
    }

    public DeleteScreen2(JPanel resultsContainer){
        setSize(500, 375);
        this.setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel main = new JPanel(new BorderLayout());

        JPanel searchPromptContainer = new JPanel(new BorderLayout());
//        searchPromptContainer.setBorder(BorderFactory.createLineBorder(Color.black));
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


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = name.getText();
                System.out.println("im running not");

//                performSearch(userInput);

                int listSize = contactController.getContacts().size();
                System.out.println(listSize);
                boolean found = false;
                for(int i = 0; i < listSize; i++){
                    if(contactController.getContacts().get(i).getName().equals(userInput)) {
                        found = true;
                        int input = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to delete this contact? It cannot be undone!", "Select an Option...", JOptionPane.YES_NO_OPTION);

                        // 0=yes, 1=no, 2=cancel
                        System.out.println(input);
                        if(input == 0) {
                            contactController.getContacts().remove(contactController.getContacts().get(i));
                        }
                        dispose();
                    }
                }
                if(!found){

                    new SearchScreen(noLocate());
                    dispose();
                }


//

            }
        });
    }




}
