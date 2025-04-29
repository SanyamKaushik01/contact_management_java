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
			System.out.println("-> Add Contact");
			System.out.println("-> View Contacts");
			System.out.println("-> Search Contact");
			System.out.println("-> Update Contact");
			System.out.println("-> Delete Contact");
			System.out.println("-> Exit");
			System.out.print("Enter your choice: ");
			String choice = sc.nextLine().toLowerCase();

			switch (choice) {
			case "add":
				System.out.print("Enter Name: ");
				String name = sc.nextLine();
				System.out.print("Enter Phone: ");
				String phone = sc.nextLine();
				System.out.print("Enter Email: ");
				String email = sc.nextLine();
				System.out.print("Enter Address: ");
				String address = sc.nextLine();
				
				
				Contact newContact = new Contact(name, phone, email, address);
				if (contactService.isPhoneTaken(phone, "")) {
				    System.out.println("Phone number already exists! Contact not added.");
				    break;
				}
				contactService.addContact(newContact);
				System.out.println("Contact added successfully!");
				break;

			case "view":
				List<Contact> contacts = contactService.viewContacts();
				if (contacts.isEmpty()) {
					System.out.println("No contacts found!");
				} else {
					for (Contact c : contacts) {
						System.out.println(c.getName() + "-" + c.getPhone());
						System.out.println("---------------------");
					}
					
				}
				break;

			case "search":
			    System.out.println("Search by: name / phone / email / address");
			    String searchField = sc.nextLine();
			    System.out.print("Enter value to search: ");
			    String searchValue = sc.nextLine();

			    List<Contact> contact1=contactService.searchByField(searchField, searchValue);
			    for(Contact c:contact1) {
			    	System.out.println(c);
			    }
			    break;


			case "update":
			    System.out.println("Update by: name / phone / email / address");
			    String field = sc.nextLine();
			    System.out.print("Enter value to search for update: ");
			    String fieldValue = sc.nextLine();

			    List<Contact> matches = contactService.searchByField(field, fieldValue);

			    if (matches.isEmpty()) {
			        System.out.println("No contact found for update.");
			        break;
			    }

			    System.out.println("Matching contacts:");
			    for (Contact contact : matches) {
			        System.out.println(contact);
			    }

			    System.out.print("Enter the exact Name of the contact you want to update: ");
			    String selectedName = sc.nextLine();

			    Contact toUpdate = null;
			    for (Contact contact : matches) {
			        if (contact.getName().equalsIgnoreCase(selectedName)) {
			            toUpdate = contact;
			            break;
			        }
			    }

			    if (toUpdate == null) {
			        System.out.println("No contact with the given name found in search results. Update cancelled.");
			        break;
			    }

			    System.out.println("Leave field blank to keep old value.");

			    System.out.print("Enter New Name: ");
			    String newName = sc.nextLine();
			    if (newName.isEmpty()) newName = toUpdate.getName();

			    System.out.print("Enter New Phone: ");
			    String newPhone = sc.nextLine();
			    if (newPhone.isEmpty()) newPhone = toUpdate.getPhone();

			    if (contactService.isPhoneTaken(newPhone, toUpdate.getName())) {
			        System.out.println("Phone number already exists with another contact. Update cancelled.");
			        break;
			    }

			    System.out.print("Enter New Email: ");
			    String newEmail = sc.nextLine();
			    if (newEmail.isEmpty()) newEmail = toUpdate.getEmail();

			    System.out.print("Enter New Address: ");
			    String newAddress = sc.nextLine();
			    if (newAddress.isEmpty()) newAddress = toUpdate.getAddress();

			    Contact updated = new Contact(newName, newPhone, newEmail, newAddress);
			    contactService.updateContact(toUpdate.getName(), updated);
			    System.out.println("Contact updated successfully!");
			    break;


			case "delete":
				System.out.print("Enter Name to Delete: ");
				String deleteName = sc.nextLine();
				boolean isDeleted = contactService.deleteContact(deleteName);
				if (isDeleted) {
					System.out.println("Contact deleted successfully!");
				} else {
					System.out.println("Contact not found!");
				}
				break;

			case "exit":
				System.out.println("Thank you for using the Contact Management System!");
				System.exit(0);

			default:
				System.out.println("Invalid choice! Please try again.");
			}
		}
	}
}
