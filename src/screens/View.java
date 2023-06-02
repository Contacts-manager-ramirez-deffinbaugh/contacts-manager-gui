package screens;

import contacts.Contact;
import controls.contactController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class View extends JFrame {

    public View(){
        JPanel titleContainer = new JPanel();
        JLabel title = new JLabel("Contacts");
        title.setFont(new Font("Serif", Font.PLAIN, 25));
        titleContainer.add(title);
        setTitle("Welcome");
        setSize(500, 375);
        this.setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel panel = createListingPanelForAll();
        add(BorderLayout.CENTER, new JScrollPane(panel));
        add(titleContainer, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public View(Contact contact){
        JPanel titleContainer = new JPanel();
        JLabel title = new JLabel("1 Matching Contact(s):");
        title.setFont(new Font("Serif", Font.PLAIN, 25));
        titleContainer.add(title);
        setTitle("Welcome");
        setSize(500, 375);
        this.setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel panel = createListingPanelForOne(contact);
        add(BorderLayout.CENTER, new JScrollPane(panel));
        add(titleContainer, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static JPanel addContactstoDisplay(Contact contact){
        String newWorkNo = "";
        if(contact.getWorkNumber() != 0) {
            newWorkNo = String.valueOf(contact.getWorkNumber());
            newWorkNo = newWorkNo.substring(0, 3) + "-" + newWorkNo.substring(3,6)+ "-" + newWorkNo.substring(6);
        }

        String homePhone = String.valueOf(contact.getPhoneNumber());
        homePhone = homePhone.substring(0, 3) + "-" + homePhone.substring(3,6)+ "-" + homePhone.substring(6);

        String outPut = "<html>Name: "+ contact.getName() +"<br>Home Phone: "+ homePhone +"<br>Work Phone: "+ newWorkNo + "<br>Address: "+ contact.getAddress()+ "</html>";
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setPreferredSize(new Dimension(180,75));
        JLabel label = new JLabel(outPut);
        panel.add(label);
        return panel;
    }

    public static JPanel createListingPanelForAll() {
        //TODO account for uneven size
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        int listSize = contactController.getContacts().size();
        panel.setLayout(new GridLayout(listSize/2, 2, 10, 10));
        for (int i=0; i < listSize; i++) {
            panel.add(addContactstoDisplay(contactController.getContacts().get(i)));
        }
        return panel;
    }

    public static JPanel createListingPanelForOne(Contact contact) {
        //TODO account for uneven size
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(1, 1, 10, 10));
        panel.add(addContactstoDisplay(contact));
        return panel;
    }
}
