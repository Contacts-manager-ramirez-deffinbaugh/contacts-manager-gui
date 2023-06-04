package controls;

import contacts.Contact;
import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class contactController {

    private static final ArrayList<Contact> contacts = new ArrayList<>();


    public static ArrayList<Contact> getContacts() {
        return contacts;
    }
    public static void loadContacts() {
         try {
             getContactsFromList();
         } catch (IOException e) {
             System.out.println("couldn't load contacts. idk man");
         }
     }

    public static void exit() throws IOException {
            saveContactList();
            System.exit(0);
    }
    private static List<String> getFileStringsFromContacts() {
        List<String> contactStrings = new ArrayList<>();
        for (Contact contact : getContacts()) {
            String contactString = contact.toCSV();
            contactStrings.add(contactString);
        }
        return contactStrings;
    }

    public static void saveContactList() throws IOException {
        Path filepath = Paths.get("contacts.txt");
        List<String> fileStrings = getFileStringsFromContacts();
        Files.write(filepath, fileStrings);
    }

    public static boolean contactExists(String nameInput) {
        for(Contact contact : contacts) {
            if(contact.getName().equalsIgnoreCase(nameInput)) {
                return true;
            }
        }
        return false;
    }


    private static void getContactsFromList() throws IOException {
        List<String> contactList = getFileList();
        for(String contact : contactList) {
            String[] contactSplit = contact.split(",");
            //TODO wrap conversion
            long homeNumber = Long.parseLong(contactSplit[1]);
            if(contactSplit[2].equals("")){
                Contact newContact = new Contact(contactSplit[0], homeNumber, contactSplit[3]);
                contacts.add(newContact);
            } else {
                //TODO wrap conversion
                long workNumber = Long.parseLong(contactSplit[2]);
                Contact newContact = new Contact(contactSplit[0], homeNumber, contactSplit[3],workNumber);
                contacts.add(newContact);
            }
        }
    }

    public static boolean compareName(int i, String comparison) {
        return contacts.get(i).getName().equalsIgnoreCase(comparison);
    }


    private static List<String> getFileList() throws IOException {
        Path filepath = Paths.get("contacts.txt");
        //read from file
        return Files.readAllLines(filepath);
    }

    public static int handleExistingContact(String nameInput) {
        int index = -2;
            for (int i = 0; i < contactController.getContacts().size(); i++) {
                if (contacts.get(i).getName().equalsIgnoreCase(nameInput)) {
                    String message = "<html>This contact already exits.<br>Do you want to replace it?<html>";
                    int input = JOptionPane.showConfirmDialog(null,
                            message, "Select an Option...", JOptionPane.YES_NO_OPTION);

                    // 0=yes, 1=no, 2=cancel
                    if(input == 0) {
                        index = i;
                    }

                }
            }
            return index;
    }

    public static String handleCreation(String nameInput, long homePhoneLong, String workPhone, String address) {
        long workPhoneLong;


        if(workPhone.trim().equals("") && address.trim().equals("")) {
            contacts.add(new Contact(nameInput, homePhoneLong));
        } else if(!workPhone.trim().equals("") && !address.trim().equals("")) {
            try {
                workPhoneLong = Long.parseLong(workPhone);
                if (workPhone.length() != 10 && workPhone.length() != 7) {
                    return "Please enter a valid 7 or 10 digit number.";
                }
            }
            catch(Exception e) {
                return "Please enter a valid number.";
            }
            contacts.add(new Contact(nameInput, homePhoneLong,address,workPhoneLong));
        } else if(address.trim().equals("")) {
            try {
                workPhoneLong = Long.parseLong(workPhone);
                if (workPhone.length() != 10 && workPhone.length() != 7) {
                    return "Please enter a valid 7 or 10 digit number.";
                }
            }
            catch(Exception e) {
                return "Please enter a valid number.";
            }
            contacts.add(new Contact(nameInput, homePhoneLong,workPhoneLong));
        } else {
            contacts.add(new Contact(nameInput, homePhoneLong,address));
        }

        return "Contact \"" + nameInput + "\" added successfully";
    }


    public static String handleOverwrite(String nameInput, long homePhone, String workPhone, String address, int i) {
        long workPhoneLong;



        if(workPhone.trim().equals("") && address.trim().equals("")) {
            contacts.set(i,new Contact(nameInput, homePhone));
        } else if(!workPhone.trim().equals("") && !address.trim().equals("")) {
            try {
                workPhoneLong = Long.parseLong(workPhone);
                if (workPhone.length() != 10 && workPhone.length() != 7) {
                    return "Please enter a valid 7 or 10 digit number.";
                }
            }
            catch(Exception e) {
                return "Please enter a valid number.";
            }
            contacts.set(i,new Contact(nameInput, homePhone,address,workPhoneLong));
        } else if(address.trim().equals("")) {
            try {
                workPhoneLong = Long.parseLong(workPhone);
                if (workPhone.length() != 10 && workPhone.length() != 7) {
                    return "Please enter a valid 7 or 10 digit number.";
                }
            }
            catch(Exception e) {
                return "Please enter a valid number.";
            }
            contacts.set(i,new Contact(nameInput, homePhone,workPhoneLong));
        } else {
            contacts.set(i,new Contact(nameInput, homePhone,address));
        }

        return "Contact \"" + nameInput + "\" added successfully";
    }

    public static String verifyUserInput2(String nameInput, String homePhone, String workPhone, String address) {
        if(homePhone.trim().equals("") || nameInput.trim().equals("")) {
            return "Please enter all required fields";
        }
        long homePhoneLong;
        try {
            homePhoneLong = Long.parseLong(homePhone);
            if (homePhone.length() != 10 && homePhone.length() != 7) {
                return "Please enter a valid 7 or 10 digit number.";
            }
        }
        catch(Exception e) {
            return "Please enter a valid number.";
        }



        if(contactExists(nameInput)) {
            int howToHandle = handleExistingContact(nameInput);
            if(howToHandle == -2) {
                return  "Contact \""+nameInput+ "\" not overwritten.";
            } else {
                return handleOverwrite(nameInput, homePhoneLong, workPhone, address, howToHandle);
            }
        } else {
            return handleCreation(nameInput, homePhoneLong, workPhone, address);
        }

    }

}
