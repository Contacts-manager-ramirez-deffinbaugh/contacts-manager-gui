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

    private static List<String> getFileList() throws IOException {
        Path filepath = Paths.get("contacts.txt");
        //read from file
        List<String> contactList = Files.readAllLines(filepath);
        return contactList;
    }

    public static String verifyUserInput(String nameInput, String homePhone, String workPhone, String address) {
        if(nameInput.trim().equals("") || homePhone.trim().equalsIgnoreCase("")) {

            return "Please fill out all required fields";
        }

        else {

            if(contactController.contactExists(nameInput)) {
                for (int i = 0; i < contactController.getContacts().size(); i++) {
                    if (contactController.getContacts().get(i).getName().equalsIgnoreCase(nameInput)) {
                        String message = "<html>This contact already exits.<br>Do you want to replace it?<html>";
                        int input = JOptionPane.showConfirmDialog(null,
                                message, "Select an Option...", JOptionPane.YES_NO_OPTION);

                        // 0=yes, 1=no, 2=cancel
                        if(input == 1) {
                            return "Contact \""+nameInput+ "\" not overwritten.";
                        } else {
                            try {
                                long homePhoneLong = Long.parseLong(homePhone);
                                if(homePhone.length() != 10 && homePhone.length() != 7) {
                                    return "Please enter a valid 7 or 10 digit number.";
                                }

                                if(workPhone.trim().equals("") && address.trim().equals("")) {
                                    contacts.set(i,new Contact(nameInput, homePhoneLong));
                                } else if(workPhone.trim().equals("")) {
                                    contacts.set(i,new Contact(nameInput, homePhoneLong,address));
                                }else if(address.trim().equals("")) {
                                    long workPhoneLong = Long.parseLong(workPhone);
                                    if(workPhone.length() != 10 && workPhone.length() != 7) {
                                        return "Please enter a valid 7 or 10 digit number.";
                                    }
                                    contacts.set(i,new Contact(nameInput, homePhoneLong,workPhoneLong));
                                } else {
                                    long workPhoneLong = Long.parseLong(workPhone);
                                    if(workPhone.length() != 10 && workPhone.length() != 7) {
                                        return "Please enter a valid 7 or 10 digit number.";
                                    }
                                    contacts.set(i,new Contact(nameInput, homePhoneLong,address,workPhoneLong));
                                }



                                //TODO check if contact exits, prompt user
                                //TODO check for number of input fields
//                addContact(nameInput, homePhoneLong);

                                return "Contact \"" + nameInput + "\" added successfully";
                            } catch (Exception ex) {
                                return "Please enter a valid number";
                            }
                        }

                    }
                }

            }

            //TODO this can be switched to phone number validation
            try {
                long homePhoneLong = Long.parseLong(homePhone);

                if(homePhone.length() != 10 && homePhone.length() != 7) {
                    return "Please enter a valid 7 or 10 digit number.";
                }


                if(workPhone.trim().equals("") && address.trim().equals("")) {
                    contacts.add(new Contact(nameInput, homePhoneLong));
                } else if(workPhone.trim().equals("")) {
                    contacts.add(new Contact(nameInput, homePhoneLong,address));
                }else if(address.trim().equals("")) {
                    long workPhoneLong = Long.parseLong(workPhone);
                    if(workPhone.length() != 10 && workPhone.length() != 7) {
                        return "Please enter a valid 7 or 10 digit number.";
                    }

                    contacts.add(new Contact(nameInput, homePhoneLong,workPhoneLong));
                } else {
                    long workPhoneLong = Long.parseLong(workPhone);
                    if(workPhone.length() != 10 && workPhone.length() != 7) {
                        return "Please enter a valid 7 or 10 digit number.";
                    }
                    contacts.add(new Contact(nameInput, homePhoneLong,address,workPhoneLong));
                }



                //TODO check if contact exits, prompt user
                //TODO check for number of input fields
//                addContact(nameInput, homePhoneLong);

                return "Contact \"" + nameInput + "\" added successfully";
            } catch (Exception ex) {
                return "Please enter a valid number";
            }
        }
    }
}
