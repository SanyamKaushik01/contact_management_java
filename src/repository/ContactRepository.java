package repository;

import entity.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private List<Contact> contacts = new ArrayList<>();

    // Add Contact
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    // Get All Contacts
    public List<Contact> getAllContacts() {
        return contacts;
    }

    // Find Contact by Name
    public Contact findByName(String name) {
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    // Delete Contact
    public boolean deleteContact(String name) {
        Contact contact = findByName(name);
        if (contact != null) {
            contacts.remove(contact);
            return true;
        }
        return false;
    }
}
