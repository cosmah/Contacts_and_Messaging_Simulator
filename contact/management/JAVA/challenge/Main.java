package contact.management.JAVA.challenge;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Contact> contacts;
    private static Scanner scanner;
    private  static int id = 0;
    public static void main(String[] args){

        //intialise
        contacts = new ArrayList<>();
        System.out.println("Welcome to My Application");

        showInitialOptions();

    }

    private static void showInitialOptions(){
        System.out.println("Please select one option:" +
                "\n1. Manage Contacts" +
                "\n2. Messages" +
                "\n3. Quit");

        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                manageContacts();
                break;

            case 2:
                manageMessages();
                break;
            default:
                break;
        }
    }

    private static void manageMessages() {
        System.out.println("Please select one: " +
                "\n1. Show all messages" +
                "\n2. Send a new message" +
                "\n3. Go back");
        int choice = scanner.nextInt();
        switch (choice){
            case  1:
                showAllMessages();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void sendNewMessage() {
        System.out.println("Enter recipient name: ");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("Please enter the name.");
            sendNewMessage();
        }else {
           boolean doesExist = false;
           for (Contact c:contacts){
               if (c.getName().equals(name)){
                   doesExist = true;
               }
           }

           if (doesExist){
               System.out.println("Type ............");
               String text = scanner.next();
               if (text.equals("")){
                   System.out.println("Please enter some stuff");
                   sendNewMessage();
               }else {
                   id++;
                   Message newMessage = new Message(text, name, id);
                   for (Contact c: contacts){
                       if (c.getName().equals(name)){
                           ArrayList<Message> newMessages = c.getMessages();
                           newMessages.add(newMessage);
                       }
                   }
               }
           }else {
               System.out.println("There is no such contact");
           }
        }
    }

    private static void showAllMessages() {
        ArrayList<Message> allMessages = new ArrayList<>();
        for (Contact c: contacts){
            allMessages.addAll(c.getMessages());
        }

        if (allMessages.size()>0){
            for (Message m: allMessages){
                m.getDetails();
                System.out.println("*************");
            }
        }else {
            System.out.println("You dont have messages");
        }
        showInitialOptions();
    }

    private  static void manageContacts(){
        System.out.println("Please select one: " +
                "\n1. Show all contacts" +
                "\n2. Add a new contact" +
                "\n3. Search for a contact" +
                "\n4. Delete a contact" +
                "\n5. Go Back");

        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void deleteContact() {
        System.out.println("Please enter contact name : ");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("Please enter name");
            deleteContact();
        }else {

            boolean doesExist = false;

            for (Contact c: contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                    contacts.remove(c);

                }
            }

            if (!doesExist){
                System.out.println("There is no such contact");
            }
        }
        showInitialOptions();
    }

    private static void searchForContact() {
        System.out.println("Please enter contact name: ");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("Please enter name");
            searchForContact();
        }else {
            boolean doesExist = false;
            for (Contact c: contacts){
                if (c.getName().equals(name)) {
                    doesExist = true;
                    c.getDetails();
                }
            }

            if (!doesExist){
                System.out.println("There is no such contact in your phone");
                showInitialOptions();
            }
        }
    }

    private static void addNewContact() {
        System.out.println("Adding new contact....." + "\nEnter Contacts Name: ");
        String name = scanner.next();

        System.out.println("Please enter the contact's number: ");
        String number = scanner.next();

        System.out.println("Please enter the contact's email: ");
        String email = scanner.next();

        if (name.equals("") || number.equals("") || email.equals("")){
            System.out.println("Please enter all of the information");
            addNewContact();
        }else{
            //check
            boolean doesExist = false;
            for (Contact c: contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                }
            }
            if (doesExist){
                System.out.println("We have a contact named " + name + " saved on this device.");
                addNewContact();
            }else {
                Contact contact = new Contact(name, number,email);
                contacts.add(contact);
                System.out.println("Contact added");
            }
        }
        showInitialOptions();
    }

    private static void showAllContacts() {
        for (Contact c: contacts){
            c.getDetails();
            System.out.println("************");
        }

        showInitialOptions();
    }
}
