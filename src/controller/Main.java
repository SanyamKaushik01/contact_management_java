package controller;

import entity.Contact;
import service.ContactService;
import service.ContactServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactService contactService = new ContactServiceImpl();

        while (true) {
            System.out.println("\n===== Contact Management System =====");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume extra newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();

                    Contact newContact = new Contact(name, phone, email, address);
                    contactService.addContact(newContact);
                    System.out.println("Contact added successfully!");
                    break;

                case 2:
                    List<Contact> contacts = contactService.viewContacts();
                    if (contacts.isEmpty()) {
                        System.out.println("No contacts found!");
                    } else {
                        for (Contact c : contacts) {
                            System.out.println(c.getName());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Name to Search: ");
                    String searchName = sc.nextLine();
                    Contact foundContact = contactService.searchContact(searchName);
                    if (foundContact != null) {
                        System.out.println("Contact Found:\n" + foundContact);
                    } else {
                        System.out.println("Contact not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Name to Update: ");
                    String oldName = sc.nextLine();
                    Contact existingContact = contactService.searchContact(oldName);
                    if (existingContact != null) {
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter New Phone: ");
                        String newPhone = sc.nextLine();
                        System.out.print("Enter New Email: ");
                        String newEmail = sc.nextLine();
                        System.out.print("Enter New Address: ");
                        String newAddress = sc.nextLine();

                        Contact updatedContact = new Contact(newName, newPhone, newEmail, newAddress);
                        contactService.updateContact(oldName, updatedContact);
                        System.out.println("Contact updated successfully!");
                    } else {
                        System.out.println("Contact not found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter Name to Delete: ");
                    String deleteName = sc.nextLine();
                    boolean isDeleted = contactService.deleteContact(deleteName);
                    if (isDeleted) {
                        System.out.println("Contact deleted successfully!");
                    } else {
                        System.out.println("Contact not found!");
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using the Contact Management System!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
