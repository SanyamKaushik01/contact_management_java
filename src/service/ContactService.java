package service;

import entity.Contact;
import java.util.List;

public interface ContactService {
    void addContact(Contact contact);
    List<Contact> viewContacts();
    Contact searchContact(String name);
    boolean deleteContact(String name);
    boolean updateContact(String name, Contact updatedContact);
}
