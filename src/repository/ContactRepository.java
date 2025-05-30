package repository;

import entity.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private List<Contact> contacts = new ArrayList<>();
    public ContactRepository() {
    	contacts.add(new Contact("Vishwajeet", "9119734412", "vishwajeetchhonker@gmail.com", "Jewar"));
    	contacts.add(new Contact("John","9876509823", "john@gmail.com", "LA"));
    	contacts.add(new Contact("Alice", "9876543210", "alice@example.com", "New York"));
    	contacts.add(new Contact("Bob", "9123456789", "bob@example.com", "Chicago"));
    	contacts.add(new Contact("Eve", "9988776655", "eve@example.com", "Houston"));
    	contacts.add(new Contact("Charlie", "9876123456", "charlie@example.com", "San Francisco"));
    	contacts.add(new Contact("David", "9786543210", "david@example.com", "Boston"));
    	contacts.add(new Contact("Sophia", "9234567890", "sophia@example.com", "Seattle"));
    	contacts.add(new Contact("Asmit", "9877098765", "mike@example.com", "Miami"));
    	contacts.add(new Contact("Olivia", "9865432190", "olivia@example.com", "Dallas"));
    	contacts.add(new Contact("Vaibhav", "9123098765", "emma@example.com", "Austin"));

    }

    // Add Contact
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    // Get All Contacts
    public List<Contact> getAllContacts() {
        contacts.sort((a,b)->a.getName().compareTo(b.getName()));
        return contacts;
    }

    // Find Contact by Name
    public Contact findByName(String name) {
    	Contact contact;
    	contact = contacts.stream().filter(c->c.getName().toLowerCase().equalsIgnoreCase(name)).findFirst().orElse(null);
    	System.out.println(contact);
    	return contact;
    }

    // Delete Contact
    public boolean deleteContact(String name) {
        Contact contact = contacts.stream().filter(c->c.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (contact != null) {
            contacts.remove(contact);
            return true;
        }
        return false;
    }
    public List<Contact> findByField(String field, String value) {
            switch (field.toLowerCase()) {
                case "name":
                	return contacts.stream().filter(k->k.getName().toLowerCase().contains(value.toLowerCase())).toList();
                case "phone":
                	return contacts.stream().filter(k->k.getPhone().contains(value)).toList();
                case "email":
                	return contacts.stream().filter(k->k.getEmail().toLowerCase().contains(value.toLowerCase())).toList();
                case "address":
                	return contacts.stream().filter(k->k.getAddress().toLowerCase().contains(value.toLowerCase())).toList();
                	default:
                		return null;
            }
        }
    public boolean phoneExists(String phone, String excludeName) {
        for (Contact c : contacts) {
            if (c.getPhone().equals(phone) && !c.getName().equalsIgnoreCase(excludeName)) {
                return true;
            }
        }
        return false;
    }
}
