import contacts.Contact;
import controls.contactController;

import javax.swing.*;
import java.util.Scanner;

public class test {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        System.out.println("enter num");
        int myNumber = Integer.parseInt(scanner.nextLine());

        if(myNumber == 5) {
            System.out.println("first one ran");
        } else if(myNumber == 2) {
            System.out.println("second one ran");
        } else if(myNumber % 2 == 0) {
            System.out.println("third one ran");
        } else {
            System.out.println("fourth one ran");
        }


    }




}
